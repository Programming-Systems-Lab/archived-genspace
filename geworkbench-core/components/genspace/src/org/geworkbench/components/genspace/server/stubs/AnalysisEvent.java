
package org.geworkbench.components.genspace.server.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for analysisEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="analysisEvent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://server.genspace.components.geworkbench.org/}lazyCycleBreaker">
 *       &lt;sequence>
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parameters" type="{http://server.genspace.components.geworkbench.org/}analysisEventParameter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tool" type="{http://server.genspace.components.geworkbench.org/}tool" minOccurs="0"/>
 *         &lt;element name="toolname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transaction" type="{http://server.genspace.components.geworkbench.org/}transaction" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "analysisEvent", propOrder = {
    "createdAt",
    "id",
    "parameters",
    "tool",
    "toolname",
    "transaction"
})
public class AnalysisEvent
    extends LazyCycleBreaker
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdAt;
    protected int id;
    @XmlElement(nillable = true)
    protected List<AnalysisEventParameter> parameters;
    protected Tool tool;
    protected String toolname;
    protected Transaction transaction;

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
     * Gets the value of the parameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnalysisEventParameter }
     * 
     * 
     */
    public List<AnalysisEventParameter> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<AnalysisEventParameter>();
        }
        return this.parameters;
    }

    /**
     * Gets the value of the tool property.
     * 
     * @return
     *     possible object is
     *     {@link Tool }
     *     
     */
    public Tool getTool() {
        return tool;
    }

    /**
     * Sets the value of the tool property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tool }
     *     
     */
    public void setTool(Tool value) {
        this.tool = value;
    }

    /**
     * Gets the value of the toolname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToolname() {
        return toolname;
    }

    /**
     * Sets the value of the toolname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToolname(String value) {
        this.toolname = value;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link Transaction }
     *     
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transaction }
     *     
     */
    public void setTransaction(Transaction value) {
        this.transaction = value;
    }

}
