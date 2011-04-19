package org.geworkbench.components.genspace.datamanagement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import gov.nih.nlm.ncbi.www.soap.eutils.EFetchSequenceServiceStub;
import gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub;

import org.geworkbench.components.genspace.ui.*;

public class Fetch
{
	String db="";
	String search="";
	String[] ids = { "" };
    String fetchIds = "";
    int count = 0;
    public String[] locus; 
    public String[] definition ;
    public String[] accession ;
    public String[] organism ;
    public String[] project ;
    public String[] sequence ;
    
    public Fetch()
	{
		    

	}

     public int searchDatabase(String dbname, String searchkey)
     {
    	db = dbname.toLowerCase();
 		search= searchkey;  
 		String[] ret = new String[6]; 
 		try
        {

            EUtilsServiceStub service = new EUtilsServiceStub();

                      
            EUtilsServiceStub.ESearchRequest req = new EUtilsServiceStub.ESearchRequest();
        	
            
            req.setDb(db);

            req.setTerm(search+"+AND+pubmed_nuccore[sb]");

            req.setSort("PublicationDate");

            req.setRetMax("5");

            EUtilsServiceStub.ESearchResult res = service.run_eSearch(req);

            // results output

            int N = res.getIdList().getId().length;
            
            

            ids[0] = "";

            for (int i = 0; i < N; i++)

            {

                if (i > 0) ids[0] += ",";

                ids[0] += res.getIdList().getId()[i];

            }

            System.out.println("Searching in "+db+" for "+search);//+" returned " + res.getCount() + " hits");

            //System.out.println("Search links in nuccore for the first "+N+" UIDs: "+ids[0]);

            System.out.println();

        }

        catch (Exception e) { System.out.println(e.toString()); }

        // STEP #2: get links in nucleotide database (nuccore)

        //

        try

        {

            EUtilsServiceStub service = new EUtilsServiceStub();

            // call NCBI ELink utility

            EUtilsServiceStub.ELinkRequest req = new EUtilsServiceStub.ELinkRequest();

            req.setDb("nuccore");

            req.setDbfrom(db);

            req.setId(ids);

            EUtilsServiceStub.ELinkResult res = service.run_eLink(req);

            for (int i = 0; i < res.getLinkSet()[0].getLinkSetDb()[0].getLink().length; i++)

            {

                if (i > 0) fetchIds += ",";

                fetchIds += res.getLinkSet()[0].getLinkSetDb()[0].getLink()[i].getId().getString();

            }

            //System.out.println("ELink returned the following UIDs from nuccore: " + fetchIds);

            System.out.println();

        

        }

        catch (Exception e) { System.out.println(e.toString()); }

        // STEP #3: fetch records from nuccore

        //

        try

        {

            EFetchSequenceServiceStub service = new EFetchSequenceServiceStub();

            // call NCBI EFetch utility

            EFetchSequenceServiceStub.EFetchRequest req = new EFetchSequenceServiceStub.EFetchRequest();

            req.setDb("nuccore");

            req.setId(fetchIds);

            EFetchSequenceServiceStub.EFetchResult res = service.run_eFetch(req);
         
            // results output
            //SearchSwing ss = new SearchSwing();
            count=0;
            count = res.getGBSet().getGBSetSequence().length;
            
            //System.out.println("Count :"+count);
            
            locus = new String[count];
            definition = new String[count];
            accession = new String[count];
            organism = new String[count];
            project = new String[count];
            sequence = new String[count];
            
           // System.out.println("Count :"+count);
            
            
            
            for (int i = 0; i <count;i++)//res.getGBSet().getGBSetSequence().length; i++)

            {
            	
            	
                EFetchSequenceServiceStub.GBSeq_type0 obj = res.getGBSet().getGBSetSequence()[i].getGBSeq();
                
                //System.out.println("Locus: " + obj.getGBSeq_locus());
                //ss.locus.setText(obj.getGBSeq_locus());
                locus[i] = "" + obj.getGBSeq_locus();
           

                //System.out.println("Definition: " + obj.getGBSeq_definition());
                //ss.definition.setText(obj.getGBSeq_definition());
                definition[i] = "" + obj.getGBSeq_definition();
                
                
                //System.out.println("Accession: " + obj.getGBSeq_accessionVersion());
                //ss.accession.setText(obj.getGBSeq_accessionVersion());
                accession[i] = "" + obj.getGBSeq_accessionVersion();
                
                
                //System.out.println("Organism: " + obj.getGBSeq_organism());
                //ss.organism.setText(obj.getGBSeq_organism());
                organism[i] = "" + obj.getGBSeq_organism();
                                
                //System.out.println("Project: " + obj.getGBSeq_project());
                //ss.project.setText(obj.getGBSeq_project());
                project[i] = "" + obj.getGBSeq_project();
                
                
                //System.out.println("Sequence: " + obj.getGBSeq_sequence());
                //ss.sequence.setText(obj.getGBSeq_sequence());
                sequence[i] = "" + obj.getGBSeq_sequence();
                                
                            
                //ss.setVisible(true);
                
                
                //ss.repaint();              
                //System.out.println("------------------------------------------");

            }
            
            
    
            
            
            
            

        }

        catch (Exception e) { System.out.println(e.toString()); }
        return count;
		

    
	}
}
