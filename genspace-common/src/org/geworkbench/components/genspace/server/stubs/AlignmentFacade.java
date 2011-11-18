
package org.geworkbench.components.genspace.server.stubs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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
@WebService(name = "AlignmentFacade", targetNamespace = "http://msa.server.genspace.components.geworkbench.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AlignmentFacade {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "saveAlignment", targetNamespace = "http://msa.server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.SaveAlignment")
    @ResponseWrapper(localName = "saveAlignmentResponse", targetNamespace = "http://msa.server.genspace.components.geworkbench.org/", className = "org.geworkbench.components.genspace.server.stubs.SaveAlignmentResponse")
    public void saveAlignment(
        @WebParam(name = "arg0", targetNamespace = "")
        Alignment arg0);

}
