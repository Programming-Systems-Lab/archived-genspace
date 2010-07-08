package genspace.db;

/**
 * This class contains methods for reading an XML file from disk, creating the necessary Events,
 * and then using the DatabaseManager to insert those into the database.
 */

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import genspace.common.AnalysisEvent;
import genspace.common.Logger;


public class XMLLoader {

	// the Document object
	private Document dom;

	// the set of objects that need to be inserted into the database
	private ArrayList<AnalysisEvent> events;

	// the utility we'll use for writing to the database
	private DatabaseManager mgr = new DatabaseManager();



	/**
	 * This is the starting point for any other object that wants to use this one.
	 * Specify the name of the file to read and then load into the database
	 * @param file The full path to the file
	 */
	public void readAndLoad(String file) { 

		// reset the list of events
		events = new ArrayList<AnalysisEvent>();

		//parse the xml file and get the dom object
		parseXmlFile(file);

		//get each element and create objects
		parseDocument();

		System.out.println(events.size());
		// now write all the analysis events to the database
		for (AnalysisEvent event : events)
		{
			mgr.insertEvent(event);
			//System.out.println(event);
		}

	}


	/**
	 * Helper method to parse the XML file and create the Document object
	 * @param file
	 */
	private void parseXmlFile(String file)
	{
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try 
		{
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse(file);

			// System.out.println("Finished with " + file);

		}
		catch(ParserConfigurationException pce) 
		{
			pce.printStackTrace();
			if (Logger.isLogError()) Logger.logError(pce);
		}
		catch(SAXException se) 
		{
			se.printStackTrace();
			if (Logger.isLogError()) Logger.logError(se);
		}
		catch(IOException ioe) 
		{
			ioe.printStackTrace();
			if (Logger.isLogError()) Logger.logError(ioe);
		}
	}


	/**
	 * This method parses the Document object and returns an ArrayList of objects within.
	 */
	private void parseDocument(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();

		//get a nodelist of <metric> elements
		NodeList nl = docEle.getElementsByTagName("metric");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the element
				Element el = (Element)nl.item(i);
				// create the objects and put them in the "events" ArrayList
				createObjects(el);
			}
		}

	}


	/**
	 * This method creates objects from the Element and then stores them in the
	 * ArrayList. It's usually not good to have a "side effect" of altering the state of
	 * an argument (in this case, the ArrayList), but... eh? What are ya gonna do?
	 */
	private void createObjects(Element empEl) {

		String hour="0";
		String minutes="0";
		String seconds="0";
		String year="0";
		String month="0";
		String day="0";

		NodeList nl = empEl.getElementsByTagName("time");
		if(nl != null && nl.getLength() > 0) {
			Element e = (Element)nl.item(0);
			year = getTextValue(e, "year");
			month = getTextValue(e, "month");
			day = getTextValue(e, "day");
			hour = getTextValue(e, "hour");
			minutes = getTextValue(e, "minute");
			seconds = getTextValue(e, "second");

			/*
				System.out.println("year " + year);
				System.out.println("month " + month);
				System.out.println("day "+ day);
				System.out.println("hours " + hour);
				System.out.println("mins " + minutes);
				System.out.println("secs " + seconds);
			 */
		}

		String timeFormat = year + "-" + month + "-" + day + " " + hour +":" + minutes +":"+ seconds;
		//System.out.println(timeFormat);

		HashMap<String, String> parameters = new HashMap<String, String>();

		NodeList n20 = empEl.getElementsByTagName("parameter");
		if (n20 != null && n20.getLength() > 0) {
			for(int i=0; i<n20.getLength(); i++) {
				Element e = (Element)n20.item(i);
				String key = getTextValue(e, "key");
				String value = getTextValue(e, "value");
				parameters.put(key, value);
				//System.out.println("Key: " + key + "; value: " + value);
			}

		}


		// get the username
		String user = "unknown";
		NodeList nl2 = empEl.getElementsByTagName("user");
		if(nl2 != null && nl2.getLength() > 0) {
			Element e = (Element)nl2.item(0);
			user = e.getAttribute("name");
			//System.out.println(user);

			/*TODO: what do we do if they're (not) a genspace user?*/
			//String genspaceUser = e.getAttribute("genspace"); // this will be "true" or "false"
		}

		// get the host name
		String host = null;
		NodeList nl3 = empEl.getElementsByTagName("host");
		if(nl3 != null && nl3.getLength() > 0) {
			Element e = (Element)nl3.item(0);
			host = e.getAttribute("name");

			//System.out.println(host);
		}


		// get the analysis name
		String analysis = null;
		NodeList nl4 = empEl.getElementsByTagName("analysis");
		if(nl4 != null && nl4.getLength() > 0) {
			Element e = (Element)nl4.item(0);
			analysis = e.getAttribute("name");

			//System.out.println(analysis);
		}

		// get the dataset name
		String dataset = null;
		NodeList nl5 = empEl.getElementsByTagName("dataset");
		if(nl5 != null && nl5.getLength() > 0) {
			Element e = (Element)nl5.item(0);
			dataset = e.getAttribute("name");

			//System.out.println(dataset);
		}

		// get the transaction id
		String transaction_id = user + host;
		NodeList nl6 = empEl.getElementsByTagName("transaction");
		if(nl6 != null && nl6.getLength() > 0) {
			Element e = (Element)nl6.item(0);
			transaction_id += e.getAttribute("id");

			//System.out.println(transaction_id);
		}

		AnalysisEvent event = new AnalysisEvent(user, host, timeFormat, analysis, dataset, transaction_id, parameters);
		events.add(event);

	}


	/**
	 * Take a xml element and the tag name, look for the tag and get
	 * the text content
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = "";
		try {
			NodeList nl = ele.getElementsByTagName(tagName);
			if(nl != null && nl.getLength() > 0) {
				Element el = (Element)nl.item(0);
				textVal = el.getFirstChild().getNodeValue();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return textVal;
	}


	/**
	 * Calls getTextValue and returns a int value
	 */
	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}


	/* Only use this as a standalone method. It either reads a single file or, if it's a directory, all files from that directory. */
	public static void main(String[] args)
	{
		// this is for reading one file at a time
		String name = "geworkbench_log.xml"; //null;
		/*
		if (args.length == 0)
		{
			System.out.println("Please specify an XML file or directory to read.");
			Scanner scan = new Scanner(System.in);
			name = scan.nextLine();
		}
		else
			name = args[0];
		 */

		try
		{
			File file = new File(name);

			//create an instance
			XMLLoader loader = new XMLLoader();

			if (file.isDirectory())
			{
				File[] files = file.listFiles();

				for (File f : files)
				{
					// read each file
					String filename = f.getAbsolutePath();
					// only care about xml files, of course
					if (filename.contains(".xml"))
					{
						System.out.println("Reading " + filename);
						loader.readAndLoad(filename);
					}
				}
			}
			else
			{
				System.out.println("Reading " + name);
				loader.readAndLoad(name);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}


