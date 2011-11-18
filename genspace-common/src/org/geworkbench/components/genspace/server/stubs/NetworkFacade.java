
package org.geworkbench.components.genspace.server.stubs;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "NetworkFacade", targetNamespace = "http://server.genspace.components.geworkbench.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface NetworkFacade {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<org.geworkbench.components.genspace.server.stubs.User>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getProfilesByNetwork", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetProfilesByNetwork")
    @ResponseWrapper(localName = "getProfilesByNetworkResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetProfilesByNetworkResponse")
    public List<User> getProfilesByNetwork(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "joinNetwork", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.JoinNetwork")
    @ResponseWrapper(localName = "joinNetworkResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.JoinNetworkResponse")
    public void joinNetwork(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "createNetwork", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.CreateNetwork")
    @ResponseWrapper(localName = "createNetworkResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.CreateNetworkResponse")
    public void createNetwork(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "leaveNetwork", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.LeaveNetwork")
    @ResponseWrapper(localName = "leaveNetworkResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.LeaveNetworkResponse")
    public void leaveNetwork(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @return
     *     returns java.util.List<org.geworkbench.components.genspace.server.stubs.UserNetwork>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMyNetworks", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetMyNetworks")
    @ResponseWrapper(localName = "getMyNetworksResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetMyNetworksResponse")
    public List<UserNetwork> getMyNetworks();

    /**
     * 
     * @return
     *     returns java.util.List<org.geworkbench.components.genspace.server.stubs.Network>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllNetworks", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetAllNetworks")
    @ResponseWrapper(localName = "getAllNetworksResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetAllNetworksResponse")
    public List<Network> getAllNetworks();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<org.geworkbench.components.genspace.server.stubs.UserNetwork>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getNetworkRequests", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetNetworkRequests")
    @ResponseWrapper(localName = "getNetworkRequestsResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetNetworkRequestsResponse")
    public List<UserNetwork> getNetworkRequests(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "acceptNetworkRequest", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.AcceptNetworkRequest")
    @ResponseWrapper(localName = "acceptNetworkRequestResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.AcceptNetworkRequestResponse")
    public void acceptNetworkRequest(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "rejectNetworkRequest", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.RejectNetworkRequest")
    @ResponseWrapper(localName = "rejectNetworkRequestResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.RejectNetworkRequestResponse")
    public void rejectNetworkRequest(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateNetworkVisibility", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.UpdateNetworkVisibility")
    @ResponseWrapper(localName = "updateNetworkVisibilityResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.UpdateNetworkVisibilityResponse")
    public void updateNetworkVisibility(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Boolean arg1);

    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getNumberOfNetworkRequests", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetNumberOfNetworkRequests")
    @ResponseWrapper(localName = "getNumberOfNetworkRequestsResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetNumberOfNetworkRequestsResponse")
    public int getNumberOfNetworkRequests();

}
