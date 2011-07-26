
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
@WebServiceClient(name = "NetworkFacadeService", targetNamespace = "http://server.genspace.components.geworkbench.org/", wsdlLocation = "http://lasalle.cs.columbia.edu:8080/NetworkFacadeService/NetworkFacade?wsdl")
public class NetworkFacadeService
    extends Service
{

    private final static URL NETWORKFACADESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.geworkbench.components.genspace.server.stubs.NetworkFacadeService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.geworkbench.components.genspace.server.stubs.NetworkFacadeService.class.getResource(".");
            url = new URL(baseUrl, "http://lasalle.cs.columbia.edu:8080/NetworkFacadeService/NetworkFacade?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://lasalle.cs.columbia.edu:8080/NetworkFacadeService/NetworkFacade?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        NETWORKFACADESERVICE_WSDL_LOCATION = url;
    }

    public NetworkFacadeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NetworkFacadeService() {
        super(NETWORKFACADESERVICE_WSDL_LOCATION, new QName("http://server.genspace.components.geworkbench.org/", "NetworkFacadeService"));
    }

    /**
     * 
     * @return
     *     returns NetworkFacade
     */
    @WebEndpoint(name = "NetworkFacadePort")
    public NetworkFacade getNetworkFacadePort() {
        return super.getPort(new QName("http://server.genspace.components.geworkbench.org/", "NetworkFacadePort"), NetworkFacade.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NetworkFacade
     */
    @WebEndpoint(name = "NetworkFacadePort")
    public NetworkFacade getNetworkFacadePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.genspace.components.geworkbench.org/", "NetworkFacadePort"), NetworkFacade.class, features);
    }

}
