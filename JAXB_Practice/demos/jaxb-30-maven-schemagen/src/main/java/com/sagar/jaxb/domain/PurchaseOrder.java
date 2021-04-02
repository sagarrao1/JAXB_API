package com.sagar.jaxb.domain;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "items",
    "customer",
    "comment"
})
@XmlRootElement(name = "purchaseOrder")
public class PurchaseOrder {	
	
	@XmlAttribute(required = true)
//	@XmlSchemaType(name = "date")
	private Date orderDate;
	
	@XmlElementWrapper(name = "items",required = true)
	@XmlElement(name = "item")
	private List<Item> items;
	
	@XmlElement(required = true)
	private Customer customer;
	
	@XmlElement(nillable = true)
	private String comment ="Test";

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	
}
