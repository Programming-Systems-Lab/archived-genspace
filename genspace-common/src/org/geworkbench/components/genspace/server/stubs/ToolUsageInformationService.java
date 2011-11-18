
package org.geworkbench.components.genspace.server.stubs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "ToolUsageInformationService", targetNamespace = "http://server.genspace.components.geworkbench.org/", wsdlLocation = "http://:8080/ToolUsageInformationService/ToolUsageInformation?wsdl")
public class ToolUsageInformationService
    extends Service
{

    private final static URL TOOLUSAGEINFORMATIONSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.geworkbench.components.genspace.server.stubs.ToolUsageInformationService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.geworkbench.components.genspace.server.stubs.ToolUsageInformationService.class.getResource(".");
            url = new URL(baseUrl, "http://:8080/ToolUsageInformationService/ToolUsageInformation?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://:8080/ToolUsageInformationService/ToolUsageInformation?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        TOOLUSAGEINFORMATIONSERVICE_WSDL_LOCATION = url;
    }

    public ToolUsageInformationService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ToolUsageInformationService() {
        super(TOOLUSAGEINFORMATIONSERVICE_WSDL_LOCATION, new QName("http://server.genspace.components.geworkbench.org/", "ToolUsageInformationService"));
    }

    /**
     * 
     * @return
     *     returns ToolUsageInformation
     */
    @WebEndpoint(name = "ToolUsageInformationPort")
    public ToolUsageInformation getToolUsageInformationPort() {
        return super.getPort(new QName("http://server.genspace.components.geworkbench.org/", "ToolUsageInformationPort"), ToolUsageInformation.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ToolUsageInformation
     */
    @WebEndpoint(name = "ToolUsageInformationPort")
    public ToolUsageInformation getToolUsageInformationPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.genspace.components.geworkbench.org/", "ToolUsageInformationPort"), ToolUsageInformation.class, features);
    }

}
