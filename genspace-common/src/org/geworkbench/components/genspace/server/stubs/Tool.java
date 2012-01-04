
package org.geworkbench.components.genspace.server.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tool complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tool">
 *   &lt;complexContent>
 *     &lt;extension base="{http://server.genspace.components.geworkbench.org/}lazyCycleBreaker">
 *       &lt;sequence>
 *         &lt;element name="comments" type="{http://server.genspace.components.geworkbench.org/}toolComment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mostCommonParameters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mostCommonParametersCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numRating" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ratings" type="{http://server.genspace.components.geworkbench.org/}toolRating" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sumRating" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="usageCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="wfCountHead" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tool", propOrder = {
    "comments",
    "description",
    "id",
    "mostCommonParameters",
    "mostCommonParametersCount",
    "name",
    "numRating",
    "ratings",
    "sumRating",
    "usageCount",
    "wfCountHead"
})
public class Tool
    extends LazyCycleBreaker
{

    @XmlElement(nillable = true)
    protected List<ToolComment> comments;
    protected String description;
    protected int id;
    protected String mostCommonParameters;
    protected int mostCommonParametersCount;
    protected String name;
    protected int numRating;
    @XmlElement(nillable = true)
    protected List<ToolRating> ratings;
    protected int sumRating;
    protected int usageCount;
    protected int wfCountHead;

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
     * {@link ToolComment }
     * 
     * 
     */
    public List<ToolComment> getComments() {
        if (comments == null) {
            comments = new ArrayList<ToolComment>();
        }
        return this.comments;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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
     * Gets the value of the mostCommonParameters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMostCommonParameters() {
        return mostCommonParameters;
    }

    /**
     * Sets the value of the mostCommonParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMostCommonParameters(String value) {
        this.mostCommonParameters = value;
    }

    /**
     * Gets the value of the mostCommonParametersCount property.
     * 
     */
    public int getMostCommonParametersCount() {
        return mostCommonParametersCount;
    }

    /**
     * Sets the value of the mostCommonParametersCount property.
     * 
     */
    public void setMostCommonParametersCount(int value) {
        this.mostCommonParametersCount = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * {@link ToolRating }
     * 
     * 
     */
    public List<ToolRating> getRatings() {
        if (ratings == null) {
            ratings = new ArrayList<ToolRating>();
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
     * Gets the value of the wfCountHead property.
     * 
     */
    public int getWfCountHead() {
        return wfCountHead;
    }

    /**
     * Sets the value of the wfCountHead property.
     * 
     */
    public void setWfCountHead(int value) {
        this.wfCountHead = value;
    }

}
