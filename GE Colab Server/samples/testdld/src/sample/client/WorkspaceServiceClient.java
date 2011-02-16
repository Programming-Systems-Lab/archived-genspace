/*
 * Copyright 2005,2006 WSO2, Inc. http://www.wso2.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.client;

import java.io.File;
import java.io.FileOutputStream;

import javax.activation.DataHandler;
import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.wsdl.WSDLConstants;
import org.apache.axis2.Constants;

public class WorkspaceServiceClient {

	private static EndpointReference targetEPR = new EndpointReference(
			"http://localhost:8080/axis2/services/WorkspaceService");

	public static void main(String[] args) throws Exception {
		if (args.length == 1) {
			System.out.println(args[0]);
			getSavedWorkspace(args[0]);
		} else {
			throw new IllegalArgumentException("Please provide the project name as an argument.");
		}
	}

	public static void getSavedWorkspace(String projectName) throws Exception {

		Options options = new Options();
		options.setTo(targetEPR);
		options.setAction("urn:getWorkspace");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		
		
		/*
		 * Uncomment to enable client side file caching for the response.
         */
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "axis2cache");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");

		options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
		
		// Increase the time out to receive large attachments
		options.setTimeOutInMilliSeconds(300000);
		
		ServiceClient sender = new ServiceClient();
		sender.setOptions(options);
		OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
        
        MessageContext mc = new MessageContext();
        SOAPEnvelope env = createEnvelope(projectName);
        mc.setEnvelope(env);
        
		mepClient.addMessageContext(mc);
		mepClient.execute(true);
		
		// Let's get the message context for the response
		MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
		SOAPBody body = response.getEnvelope().getBody();
		OMElement element = body.getFirstChildWithName(new QName("http://service.sample/xsd","getWorkspaceResponse"));
        if (element!=null)
        {
		processResponse(response, element);
        }else{
            throw new Exception("Malformed response.");
        }
	}

	private static void processResponse(MessageContext response, OMElement element) throws Exception {
		System.out.println("Project Name : "
				+ element.getFirstChildWithName(new QName("http://service.sample/xsd","projectName")).getText());
		System.out.println("Month : " + element.getFirstChildWithName(new QName("http://service.sample/xsd","month")).getText());
		System.out.println("Downloads : "
				+ element.getFirstChildWithName(new QName("http://service.sample/xsd","downloads")).getText());

		OMElement wspElement = element.getFirstChildWithName(new QName("http://service.sample/xsd","wsp"));
        //retrieving the ID of the attachment
		String wspBinID = wspElement.getAttributeValue(new QName("href"));
        //remove the "cid:" prefix
        wspBinID = wspBinID.substring(4);
		//Accesing the attachment from the response message context using the ID
        System.out.println(wspBinID);
		DataHandler dataHandler = response.getAttachment(wspBinID);
        if (dataHandler!=null){
		// Writing the attachment data (wsp binary) to a file
		File wspFile = new File("response.wsp");
		FileOutputStream outputStream = new FileOutputStream(wspFile);
		dataHandler.writeTo(outputStream);
		outputStream.flush();
		System.out.println("Download workspace saved to :" + wspFile.getAbsolutePath());
        }else
        {
            throw new Exception("Cannot find the data handler.");
        }
	}

	private static SOAPEnvelope createEnvelope(String destinationFile) {
		SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
		SOAPEnvelope env = fac.getDefaultEnvelope();
		OMNamespace omNs = fac.createOMNamespace("http://service.sample/xsd",
				"swa");
		OMElement wspElement = fac.createOMElement("getWorkspace", omNs);
		OMElement nameEle = fac.createOMElement("projectName", omNs);
		nameEle.setText(destinationFile);
		wspElement.addChild(nameEle);
		env.getBody().addChild(wspElement);
		return env;
	}
}
