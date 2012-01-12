package org.geworkbench.components.genspace.server.msa;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.geworkbench.components.genspace.entity.msa.Alignment;
import org.geworkbench.components.genspace.entity.msa.ProteinSequence;

import au.com.bytecode.opencsv.CSVReader;
import blast.Hit;
import blast.HitHsps;
import blast.Hsp;

@Stateless
@WebService
public class MSARecommender implements MSARecommenderLocal {

	@PersistenceContext(unitName = "genspace_persist")
	private EntityManager em;

	public MSARecommender() {
	}

	@Override
	@WebMethod
	public List<ProteinSequence> getRecommendedSequences(List<ProteinSequence> queries) {
		Map<String, List<Hit>> blastResult = null;
		try {
			StringBuilder sb = new StringBuilder();
			for (ProteinSequence query : queries) {
				sb.append(">").append(query.getAccessionNo()).append("\n")
						.append(query.getSequence()).append("\n");
			}
			blastResult = runBlast(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<ProteinSequence>();
		}

		Map<Alignment, Set<String>> alignToQuery = new HashMap<Alignment, Set<String>>();
		for (String query : blastResult.keySet()) {
			Set<String> hitSeqs = new LinkedHashSet<String>();
			for (Hit hit : blastResult.get(query)) {
				hitSeqs.add(hit.getHitDef());
			}

			String sql = "SELECT DISTINCT a "
					+ "FROM Alignment a JOIN a.sequences s "
					+ "WHERE s.accessionNo in ("
					+ listToString(hitSeqs, ",", true) + ")";
			System.out.println(sql);
			TypedQuery<Alignment> q = em.createQuery(sql, Alignment.class);

			for (Alignment align : q.getResultList()) {
				if (!alignToQuery.containsKey(align)) {
					alignToQuery.put(align, new HashSet<String>());
				}
				alignToQuery.get(align).add(query);
			}
		}

		List<ProteinSequence> result = new ArrayList<ProteinSequence>();
		for (Alignment alignment : alignToQuery.keySet()) {
			System.out.println("Alignment " + alignment.getEmblId() + " has "
					+ alignment.getSequences().size() + " sequences");
			for (ProteinSequence sequence : alignment.getSequences()) {
				boolean isQuery = false;
				for (ProteinSequence query : queries) {
					if (compare(query.getSequence(), sequence.getSequence(), 0) > 99.9) {
						isQuery = true;
						break;
					}
				}
				if (isQuery || result.contains(sequence)) {
					continue;
				}

				result.add(sequence);
			}
		}

		return result;
	}

	private float compare(String si, String sj, int start) {
		int ilen = si.length() - 1;
		int jlen = sj.length() - 1;

		while (isGap(si.charAt(start + ilen))) {
			ilen--;
		}

		while (isGap(sj.charAt(start + jlen))) {
			jlen--;
		}

		int match = 0;
		float pid = -1;

		if (ilen > jlen) {
			for (int j = 0; j < jlen; j++) {
				if (si.substring(start + j, start + j + 1).equals(
						sj.substring(start + j, start + j + 1))) {
					match++;
				}
			}

			pid = (float) match / (float) ilen * 100;
		} else {
			for (int j = 0; j < ilen; j++) {
				if (si.substring(start + j, start + j + 1).equals(
						sj.substring(start + j, start + j + 1))) {
					match++;
				}
			}

			pid = (float) match / (float) jlen * 100;
		}

		return pid;
	}

	private boolean isGap(char c) {
		return (c == '-' || c == '.' || c == ' ') ? true : false;
	}

	private String listToString(Collection<?> sqlList, String delimiter,
			boolean quote) {
		StringBuilder constructedSQL = new StringBuilder();

		for (Object subSQL : sqlList) {
			if (quote) {
				constructedSQL.append("\"");
			}
			constructedSQL.append(subSQL);
			if (quote) {
				constructedSQL.append("\"");
			}
			constructedSQL.append(delimiter);
		}

		if (sqlList.size() != 0) {
			return constructedSQL.substring(0, constructedSQL.length()
					- delimiter.length());

		} else {
			return "";
		}
	}

	private Map<String, List<Hit>> runBlast(String query)
			throws IOException {
		String blastAllPath = "/Users/ningyu18/Downloads/COMS6901/FALL2011/ncbi-blast-2.2.25+/bin/blastp";
		String blastDB = "/Users/ningyu18/gsc/projects/genspace/genspace-ejb/data/BlastDB/embl_align";

		File infile = File.createTempFile("blast", ".fa");
		File outfile = File.createTempFile("blast", ".csv");

		PrintWriter out = new PrintWriter(infile);
		out.println(query);
		out.flush();
		out.close();

		ProcessBuilder pb = new ProcessBuilder(blastAllPath, "-task", "blastp",
				"-db", blastDB, "-outfmt", "10", "-max_target_seqs", "1",
				"-query", infile.getAbsolutePath(), "-out",
				outfile.getAbsolutePath());
		Process proc = pb.start();
		try {
			if (proc.waitFor() != 0) {
				return new HashMap<String, List<Hit>>();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return new HashMap<String, List<Hit>>();
		}
		CSVReader reader = new CSVReader(new FileReader(outfile));

		Map<String, List<Hit>> hits = new HashMap<String, List<Hit>>();
		for (String[] line : reader.readAll()) {
			String query_def = line[0];
			if (!hits.containsKey(query_def)) {
				hits.put(query_def, new ArrayList<Hit>());
			}

			Hit hit = new Hit();
			hit.setHitDef(line[1].split("/")[0]);
			Hsp hsp = new Hsp();
			hsp.setHspEvalue(line[10]);
			HitHsps hitHsps = new HitHsps();
			hitHsps.getHsp().add(hsp);
			hit.setHitHsps(hitHsps);

			hits.get(query_def).add(hit);
		}
		reader.close();
		return hits;
		//
		// outfile.delete();
		// infile.delete();
	}

	public static void main(String[] args) {
		try {
			ProteinSequence query1 = new ProteinSequence();
			query1.setAccessionNo("Z29573/1-768");
			query1.setSequence("GCAAGTTTCCGCTACCCAGTGAGAATGCCCTTTAAGTCTTATAAATTAAGCAAAAGGAGCTGGTATCAGGCA"
					+ "CACAAAATGTAGCCGATAACACCTTGCTTTACCACACCCCCACGGGAGACAGCAGTGATTAAAATTAAGCAA"
					+ "TAAACGAAAGTTTGACTAAGTCATAATTTACATTAGGGTTGGTCAATTTCGTGCCAGCCACCGCGGTCATAC"
					+ "GATTAACCCAAATTAATAAATAACGGCGTAAAGAGTGTTTAAGTTATATACAAAAATAAAGTTAATAATTAA"
					+ "CTAAACTGTAGCACGTTCTAGTTAATATTAAAATACATAATAAAAATGACTTTAATATCACCGACTACACGA"
					+ "AAACTAAGACACAAACTGGGATTAGATACCCCACTATGCTTAGTAATAAACTAAAATAATTTAACAAACAAA"
					+ "ATTATTCGCCAGAGAACTACTAGCAATTGCTTAAAACTCAAAGGACTTGGCGGTGCCCTAAACCCACCTAGA"
					+ "GGAGCCTGTTCTATAATCGATAAACCCCGATAAACCAGACCTTATCTTGCCAATACAGCCTATATACCGCCA"
					+ "TCGTCAGCTAACCTTTAAAAAGAATTACAGTAAGCAAAATCATACAACATAAAAACGTTAGGTCAAGGTGTA"
					+ "GCATATGATAAGGAAAGTAATGGGCTACATTCTCTACTATAGAGCATAACGAATCATATTATGAAACTAAAA"
					+ "TGCTTGAAGGAGGATTTAGTAGTAAATTAAGAATAGAGAGCTTAATTG\n");

			List<ProteinSequence> recommendations = (new MSARecommender())
					.getRecommendedSequences((Arrays
							.asList(new ProteinSequence[] { query1 })));

			for (ProteinSequence sequence : recommendations) {
				System.out.print(sequence.getAccessionNo() + " matched ");
//				System.out.print(listToString(recommendations.get(sequence),
//						",", false));
				System.out.println(" and appeared in "
						+ sequence.getAlignments().size() + " alignments");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
