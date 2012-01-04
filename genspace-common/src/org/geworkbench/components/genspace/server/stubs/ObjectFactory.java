
package org.geworkbench.components.genspace.server.stubs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geworkbench.components.genspace.server.stubs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetRecommendedSequences_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "getRecommendedSequences");
    private final static QName _GetRecommendedSequencesResponse_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "getRecommendedSequencesResponse");
    private final static QName _Alignment_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "alignment");
    private final static QName _ProteinSequence_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "proteinSequence");
    private final static QName _Reference_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "reference");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geworkbench.components.genspace.server.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Reference }
     * 
     */
    public Reference createReference() {
        return new Reference();
    }

    /**
     * Create an instance of {@link GetRecommendedSequences }
     * 
     */
    public GetRecommendedSequences createGetRecommendedSequences() {
        return new GetRecommendedSequences();
    }

    /**
     * Create an instance of {@link ProteinSequence }
     * 
     */
    public ProteinSequence createProteinSequence() {
        return new ProteinSequence();
    }

    /**
     * Create an instance of {@link GetRecommendedSequencesResponse }
     * 
     */
    public GetRecommendedSequencesResponse createGetRecommendedSequencesResponse() {
        return new GetRecommendedSequencesResponse();
    }

    /**
     * Create an instance of {@link Alignment }
     * 
     */
    public Alignment createAlignment() {
        return new Alignment();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecommendedSequences }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://msa.server.genspace.components.geworkbench.org/", name = "getRecommendedSequences")
    public JAXBElement<GetRecommendedSequences> createGetRecommendedSequences(GetRecommendedSequences value) {
        return new JAXBElement<GetRecommendedSequences>(_GetRecommendedSequences_QNAME, GetRecommendedSequences.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecommendedSequencesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://msa.server.genspace.components.geworkbench.org/", name = "getRecommendedSequencesResponse")
    public JAXBElement<GetRecommendedSequencesResponse> createGetRecommendedSequencesResponse(GetRecommendedSequencesResponse value) {
        return new JAXBElement<GetRecommendedSequencesResponse>(_GetRecommendedSequencesResponse_QNAME, GetRecommendedSequencesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Alignment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://msa.server.genspace.components.geworkbench.org/", name = "alignment")
    public JAXBElement<Alignment> createAlignment(Alignment value) {
        return new JAXBElement<Alignment>(_Alignment_QNAME, Alignment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProteinSequence }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://msa.server.genspace.components.geworkbench.org/", name = "proteinSequence")
    public JAXBElement<ProteinSequence> createProteinSequence(ProteinSequence value) {
        return new JAXBElement<ProteinSequence>(_ProteinSequence_QNAME, ProteinSequence.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://msa.server.genspace.components.geworkbench.org/", name = "reference")
    public JAXBElement<Reference> createReference(Reference value) {
        return new JAXBElement<Reference>(_Reference_QNAME, Reference.class, null, value);
    }

}
