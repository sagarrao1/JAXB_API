package com.sagar.jaxb.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = {"name", "loyalty","shippingAddress","billingAddress"})
public class Customer {
	
	@XmlElement(required = true)
	private String name;
	@XmlTransient
	private Address shippingAddress;
	
	@XmlElement(name = "billToAddress", required = true)
	private Address billingAddress;
	
	@XmlElement(defaultValue = "BRONZE")
	private Loyalty loyalty /* = Loyalty.BRONZE */;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "shipToAddress" , required = true)
	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Loyalty getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(Loyalty loyalty) {
		this.loyalty = loyalty;
	}

}
