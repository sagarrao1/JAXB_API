
package com.sagar.jaxb.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loyalty" type="{http://www.sagar.com/jaxb}loyalty" minOccurs="0"/>
 *         &lt;element name="shipToAddress" type="{http://www.sagar.com/jaxb}address"/>
 *         &lt;element name="billToAddress" type="{http://www.sagar.com/jaxb}address"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customer", propOrder = {
    "name",
    "loyalty",
    "shipToAddress",
    "billToAddress"
})
public class Customer {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(defaultValue = "BRONZE")
    @XmlSchemaType(name = "string")
    protected Loyalty loyalty;
    @XmlElement(required = true)
    protected Address shipToAddress;
    @XmlElement(required = true)
    protected Address billToAddress;

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
     * Gets the value of the loyalty property.
     * 
     * @return
     *     possible object is
     *     {@link Loyalty }
     *     
     */
    public Loyalty getLoyalty() {
        return loyalty;
    }

    /**
     * Sets the value of the loyalty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Loyalty }
     *     
     */
    public void setLoyalty(Loyalty value) {
        this.loyalty = value;
    }

    /**
     * Gets the value of the shipToAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getShipToAddress() {
        return shipToAddress;
    }

    /**
     * Sets the value of the shipToAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setShipToAddress(Address value) {
        this.shipToAddress = value;
    }

    /**
     * Gets the value of the billToAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getBillToAddress() {
        return billToAddress;
    }

    /**
     * Sets the value of the billToAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setBillToAddress(Address value) {
        this.billToAddress = value;
    }

}
