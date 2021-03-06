
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
@WebServiceClient(name = "PublicFacadeService", targetNamespace = "http://server.genspace.components.geworkbench.org/", wsdlLocation = "http://genspace.cs.columbia.edu:8080/PublicFacadeService/PublicFacade?wsdl")
public class PublicFacadeService
    extends Service
{

    private final static URL PUBLICFACADESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.geworkbench.components.genspace.server.stubs.PublicFacadeService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.geworkbench.components.genspace.server.stubs.PublicFacadeService.class.getResource(".");
            url = new URL(baseUrl, "http://genspace.cs.columbia.edu:8080/PublicFacadeService/PublicFacade?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://genspace.cs.columbia.edu:8080/PublicFacadeService/PublicFacade?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        PUBLICFACADESERVICE_WSDL_LOCATION = url;
    }

    public PublicFacadeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PublicFacadeService() {
        super(PUBLICFACADESERVICE_WSDL_LOCATION, new QName("http://server.genspace.components.geworkbench.org/", "PublicFacadeService"));
    }

    /**
     * 
     * @return
     *     returns PublicFacade
     */
    @WebEndpoint(name = "PublicFacadePort")
    public PublicFacade getPublicFacadePort() {
        return super.getPort(new QName("http://server.genspace.components.geworkbench.org/", "PublicFacadePort"), PublicFacade.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PublicFacade
     */
    @WebEndpoint(name = "PublicFacadePort")
    public PublicFacade getPublicFacadePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.genspace.components.geworkbench.org/", "PublicFacadePort"), PublicFacade.class, features);
    }

}
