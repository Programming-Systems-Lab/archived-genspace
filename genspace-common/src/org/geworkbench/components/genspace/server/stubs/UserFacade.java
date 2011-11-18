
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
@WebService(name = "UserFacade", targetNamespace = "http://server.genspace.components.geworkbench.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserFacade {


    /**
     * 
     * @return
     *     returns org.geworkbench.components.genspace.server.stubs.WorkflowFolder
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getRootFolder", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetRootFolder")
    @ResponseWrapper(localName = "getRootFolderResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetRootFolderResponse")
    public WorkflowFolder getRootFolder();

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "userExists", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.UserExists")
    @ResponseWrapper(localName = "userExistsResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.UserExistsResponse")
    public boolean userExists(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

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
     *     returns org.geworkbench.components.genspace.server.stubs.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMe", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetMe")
    @ResponseWrapper(localName = "getMeResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetMeResponse")
    public User getMe();

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.UpdateUserResponse")
    public void updateUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.geworkbench.components.genspace.server.stubs.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getProfile", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetProfile")
    @ResponseWrapper(localName = "getProfileResponse", targetNamespace = "http://server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.GetProfileResponse")
    public User getProfile(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
