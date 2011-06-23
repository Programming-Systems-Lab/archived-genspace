
package org.geworkbench.components.genspace.server.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for workflow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cachedChildrenCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cachedParentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="children" type="{http://server.genspace.components.geworkbench.org/}workflow" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://server.genspace.components.geworkbench.org/}workflowComment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://server.genspace.components.geworkbench.org/}user" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idstr" type="{http://www.w3.org/2001/XMLSchema}ID" minOccurs="0"/>
 *         &lt;element name="numRating" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parent" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="ratings" type="{http://server.genspace.components.geworkbench.org/}workflowRating" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sumRating" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="toolIds" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tools" type="{http://server.genspace.components.geworkbench.org/}workflowTool" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="usageCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflow", propOrder = {
    "cachedChildrenCount",
    "cachedParentId",
    "children",
    "comments",
    "createdAt",
    "creator",
    "id",
    "idstr",
    "numRating",
    "parent",
    "ratings",
    "sumRating",
    "toolIds",
    "tools",
    "usageCount"
})
public class Workflow {

    protected int cachedChildrenCount;
    protected int cachedParentId;
    @XmlElement(nillable = true)
    protected List<Workflow> children;
    @XmlElement(nillable = true)
    protected List<WorkflowComment> comments;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdAt;
    protected User creator;
    protected int id;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idstr;
    protected int numRating;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object parent;
    @XmlElement(nillable = true)
    protected List<WorkflowRating> ratings;
    protected int sumRating;
    @XmlElement(nillable = true)
    protected List<Integer> toolIds;
    @XmlElement(nillable = true)
    protected List<WorkflowTool> tools;
    protected int usageCount;
    @XmlAttribute
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object ref;

    /**
     * Gets the value of the cachedChildrenCount property.
     * 
     */
    public int getCachedChildrenCount() {
        return cachedChildrenCount;
    }

    /**
     * Sets the value of the cachedChildrenCount property.
     * 
     */
    public void setCachedChildrenCount(int value) {
        this.cachedChildrenCount = value;
    }

    /**
     * Gets the value of the cachedParentId property.
     * 
     */
    public int getCachedParentId() {
        return cachedParentId;
    }

    /**
     * Sets the value of the cachedParentId property.
     * 
     */
    public void setCachedParentId(int value) {
        this.cachedParentId = value;
    }

    /**
     * Gets the value of the children property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the children property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildren().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Workflow }
     * 
     * 
     */
    public List<Workflow> getChildren() {
        if (children == null) {
            children = new ArrayList<Workflow>();
        }
        return this.children;
    }

    /**
     * Gets the value of the comments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WorkflowComment }
     * 
     * 
     */
    public List<WorkflowComment> getComments() {
        if (comments == null) {
            comments = new ArrayList<WorkflowComment>();
        }
        return this.comments;
    }

    /**
     * Gets the value of the createdAt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the value of the createdAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedAt(XMLGregorianCalendar value) {
        this.createdAt = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setCreator(User value) {
        this.creator = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the idstr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdstr() {
        return idstr;
    }

    /**
     * Sets the value of the idstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdstr(String value) {
        this.idstr = value;
    }

    /**
     * Gets the value of the numRating property.
     * 
     */
    public int getNumRating() {
        return numRating;
    }

    /**
     * Sets the value of the numRating property.
     * 
     */
    public void setNumRating(int value) {
        this.numRating = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setParent(Object value) {
        this.parent = value;
    }

    /**
     * Gets the value of the ratings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ratings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRatings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WorkflowRating }
     * 
     * 
     */
    public List<WorkflowRating> getRatings() {
        if (ratings == null) {
            ratings = new ArrayList<WorkflowRating>();
        }
        return this.ratings;
    }

    /**
     * Gets the value of the sumRating property.
     * 
     */
    public int getSumRating() {
        return sumRating;
    }

    /**
     * Sets the value of the sumRating property.
     * 
     */
    public void setSumRating(int value) {
        this.sumRating = value;
    }

    /**
     * Gets the value of the toolIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the toolIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getToolIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getToolIds() {
        if (toolIds == null) {
            toolIds = new ArrayList<Integer>();
        }
        return this.toolIds;
    }

    /**
     * Gets the value of the tools property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tools property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTools().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WorkflowTool }
     * 
     * 
     */
    public List<WorkflowTool> getTools() {
        if (tools == null) {
            tools = new ArrayList<WorkflowTool>();
        }
        return this.tools;
    }

    /**
     * Gets the value of the usageCount property.
     * 
     */
    public int getUsageCount() {
        return usageCount;
    }

    /**
     * Sets the value of the usageCount property.
     * 
     */
    public void setUsageCount(int value) {
        this.usageCount = value;
    }

    /**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRef(Object value) {
        this.ref = value;
    }

}
