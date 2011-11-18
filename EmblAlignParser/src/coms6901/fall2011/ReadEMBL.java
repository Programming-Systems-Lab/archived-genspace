package coms6901.fall2011;

import jalview.datamodel.SequenceI;
import jalview.io.ClustalFile;
import jalview.io.FastaFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.biojava.bio.Annotation;
import org.biojava.bio.seq.io.ReferenceAnnotation;
import org.biojava.bio.seq.io.SeqIOTools;
import org.geworkbench.components.genspace.entity.msa.Alignment;
import org.geworkbench.components.genspace.entity.msa.Reference;

public class ReadEMBL {
	private static final FastaFile fastaFile = new FastaFile();

	public static void main(String[] args) {
		// AlignmentFacade alignmentFacade = (new
		// AlignmentFacadeService()).getAlignmentFacadePort();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("genspace_persist");
		EntityManager em = emf.createEntityManager();

		File blastFile = new File("embl_align.fasta");
		if (blastFile.exists()) {
			blastFile.delete();
		}
		FileWriter blastWriter = null;
		try {
			blastWriter = new FileWriter(blastFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		
		// This filter only returns directories
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return !file.isDirectory() && file.getName().startsWith("ALIGN_") && file.getName().endsWith(".dat");
			}
		};
		
		for (File dfile : new File(".").listFiles(fileFilter)) {
			String dfilename = dfile.getName();
			System.out.println("Processing " + dfilename + "\n");

			em.getTransaction().begin();
			
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(dfile));

				// read the EMBL File
				// iterate through the sequences
				Annotation annotation = SeqIOTools.readEmbl(br).nextSequence()
						.getAnnotation();

				Alignment alignment = new Alignment();
				alignment.setReferences(new LinkedHashSet<Reference>());
				alignment
						.setSequences(new LinkedHashSet<org.geworkbench.components.genspace.entity.msa.Sequence>());
				alignment.setEmblId((String) annotation.getProperty("AC"));
				alignment.setKeywords(mergeLines(annotation, "KW"));
				alignment.setComments(mergeLines(annotation, "CC"));
				alignment.setDefinition(mergeLines(annotation, "DE"));

				em.persist(alignment);

				ClustalFile clustalFile = new ClustalFile(dfilename.replaceFirst(".dat$", ".aln"), "File");
				List<String> sos = (List<String>) annotation.getProperty("SO");
				for (ListIterator<String> it = sos.listIterator(); it.hasNext(); ) {
					String so = it.next();
					
					org.geworkbench.components.genspace.entity.msa.Sequence emblSeq = new org.geworkbench.components.genspace.entity.msa.Sequence();
					emblSeq.setAbbrevation(so.substring(4, 21).trim());
					emblSeq.setAccessionNo(so.substring(21, 34).trim());
					emblSeq.setDescription(so.substring(34).trim());
					emblSeq.setAlignments(new LinkedHashSet<Alignment>());
					emblSeq.getAlignments().add(alignment);

					Query q = em
							.createQuery("select object(s) from Sequence as s where s.accessionNo=:accessionNo");
					q.setParameter("accessionNo", emblSeq.getAccessionNo());
					try {
						emblSeq = (org.geworkbench.components.genspace.entity.msa.Sequence) q
								.getSingleResult();
						emblSeq.getAlignments().add(alignment);
						em.merge(emblSeq);
					} catch (NoResultException e) {
						em.persist(emblSeq);
						SequenceI jalSeq = (SequenceI) clustalFile.getSeqs().get(it.nextIndex()-1);						
						jalSeq = jalSeq.deriveSequence().getDatasetSequence();
						jalSeq.setName(emblSeq.getAccessionNo());
						String string = fastaFile.print(new SequenceI [] {jalSeq});
						blastWriter.write(string);
					}

					alignment.getSequences().add(emblSeq);
				}

				if (annotation.getProperty(ReferenceAnnotation.class) instanceof Annotation) {
					Reference reference = createReference((Annotation) annotation
							.getProperty(ReferenceAnnotation.class));
					reference.setAlignment(alignment);
					alignment.getReferences().add(reference);
					em.persist(reference);
				} else {
					List<Annotation> refs = (List<Annotation>) annotation
							.getProperty(ReferenceAnnotation.class);
					for (Annotation ref : refs) {
						Reference reference = createReference(ref);
						reference.setAlignment(alignment);
						alignment.getReferences().add(reference);
						em.persist(reference);
					}
				}
				em.merge(alignment);

			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				em.getTransaction().commit();
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			blastWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}

	private static Reference createReference(Annotation ref) {
		Reference reference = new Reference();
		reference.setAuthors(mergeLines(ref, "RA"));
		reference.setTitle(mergeLines(ref, "RT"));
		reference.setLocator(mergeLines(ref, "RL"));
		return reference;
	}

	private static String mergeLines(Annotation ref, String key) {
		Object title = ref.getProperty(key);
		if (title instanceof String) {
			return (String) title;
		} else if (title instanceof List) {
			StringBuffer sb = new StringBuffer();
			List<String> strings = (List<String>) title;
			for (String string : strings) {
				sb.append(string).append(" ");
			}
			return sb.toString();
		}
		throw new RuntimeException("Invalid property for " + ref);
	}
}