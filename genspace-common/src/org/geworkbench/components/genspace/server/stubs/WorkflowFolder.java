
package org.geworkbench.components.genspace.server.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for workflowFolder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workflowFolder">
 *   &lt;complexContent>
 *     &lt;extension base="{http://server.genspace.components.geworkbench.org/}lazyCycleBreaker">
 *       &lt;sequence>
 *         &lt;element name="children" type="{http://server.genspace.components.geworkbench.org/}workflowFolder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://server.genspace.components.geworkbench.org/}user" minOccurs="0"/>
 *         &lt;element name="parent" type="{http://server.genspace.components.geworkbench.org/}workflowFolder" minOccurs="0"/>
 *         &lt;element name="workflows" type="{http://server.genspace.components.geworkbench.org/}userWorkflow" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflowFolder", propOrder = {
    "children",
    "id",
    "name",
    "owner",
    "parent",
    "workflows"
})
public class WorkflowFolder
    extends LazyCycleBreaker
{

    @XmlElement(nillable = true)
    protected List<WorkflowFolder> children;
    protected int id;
    protected String name;
    protected User owner;
    protected WorkflowFolder parent;
    @XmlElement(nillable = true)
    protected List<UserWorkflow> workflows;

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
     * {@link WorkflowFolder }
     * 
     * 
     */
    public List<WorkflowFolder> getChildren() {
        if (children == null) {
            children = new ArrayList<WorkflowFolder>();
        }
        return this.children;
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
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setOwner(User value) {
        this.owner = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link WorkflowFolder }
     *     
     */
    public WorkflowFolder getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowFolder }
     *     
     */
    public void setParent(WorkflowFolder value) {
        this.parent = value;
    }

    /**
     * Gets the value of the workflows property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the workflows property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWorkflows().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserWorkflow }
     * 
     * 
     */
    public List<UserWorkflow> getWorkflows() {
        if (workflows == null) {
            workflows = new ArrayList<UserWorkflow>();
        }
        return this.workflows;
    }

}
