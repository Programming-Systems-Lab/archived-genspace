
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

    private final static QName _SaveAlignment_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "saveAlignment");
    private final static QName _Alignment_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "alignment");
    private final static QName _SaveAlignmentResponse_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "saveAlignmentResponse");
    private final static QName _Sequence_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "sequence");
    private final static QName _Reference_QNAME = new QName("http://msa.server.genspace.components.geworkbench.org/", "reference");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geworkbench.components.genspace.server.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Sequence }
     * 
     */
    public Sequence createSequence() {
        return new Sequence();
    }

    /**
     * Create an instance of {@link SaveAlignment }
     * 
     */
    public SaveAlignment createSaveAlignment() {
        return new SaveAlignment();
    }

    /**
     * Create an instance of {@link Reference }
     * 
     */
    public Reference createReference() {
        return new Reference();
    }

    /**
     * Create an instance of {@link SaveAlignmentResponse }
     * 
     */
    public SaveAlignmentResponse createSaveAlignmentResponse() {
        return new SaveAlignmentResponse();
    }

    /**
     * Create an instance of {@link Alignment }
     * 
     */
    public Alignment createAlignment() {
        return new Alignment();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveAlignment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://msa.server.genspace.components.geworkbench.org/", name = "saveAlignment")
    public JAXBElement<SaveAlignment> createSaveAlignment(SaveAlignment value) {
        return new JAXBElement<SaveAlignment>(_SaveAlignment_QNAME, SaveAlignment.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveAlignmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://msa.server.genspace.components.geworkbench.org/", name = "saveAlignmentResponse")
    public JAXBElement<SaveAlignmentResponse> createSaveAlignmentResponse(SaveAlignmentResponse value) {
        return new JAXBElement<SaveAlignmentResponse>(_SaveAlignmentResponse_QNAME, SaveAlignmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sequence }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://msa.server.genspace.components.geworkbench.org/", name = "sequence")
    public JAXBElement<Sequence> createSequence(Sequence value) {
        return new JAXBElement<Sequence>(_Sequence_QNAME, Sequence.class, null, value);
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
