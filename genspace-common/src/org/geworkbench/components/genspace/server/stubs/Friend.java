
package org.geworkbench.components.genspace.server.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for friend complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="friend">
 *   &lt;complexContent>
 *     &lt;extension base="{http://server.genspace.components.geworkbench.org/}lazyCycleBreaker">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="leftUser" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="mutual" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rightUser" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="visible" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "friend", propOrder = {
    "id",
    "leftUser",
    "mutual",
    "rightUser",
    "visible"
})
public class Friend
    extends LazyCycleBreaker
{

    protected int id;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object leftUser;
    protected boolean mutual;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object rightUser;
    protected boolean visible;

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
     * Gets the value of the leftUser property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLeftUser() {
        return leftUser;
    }

    /**
     * Sets the value of the leftUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLeftUser(Object value) {
        this.leftUser = value;
    }

    /**
     * Gets the value of the mutual property.
     * 
     */
    public boolean isMutual() {
        return mutual;
    }

    /**
     * Sets the value of the mutual property.
     * 
     */
    public void setMutual(boolean value) {
        this.mutual = value;
    }

    /**
     * Gets the value of the rightUser property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRightUser() {
        return rightUser;
    }

    /**
     * Sets the value of the rightUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRightUser(Object value) {
        this.rightUser = value;
    }

    /**
     * Gets the value of the visible property.
     * 
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     */
    public void setVisible(boolean value) {
        this.visible = value;
    }

}
