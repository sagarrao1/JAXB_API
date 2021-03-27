package com.sagar.jaxb.domain;

import java.math.BigDecimal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

public class Item {

	@XmlElement(required = true)
	private String productName;
	// Note: Because the primitive type int is used, it by default is required
	private int quantity;
	
	@XmlElement(required = true)
	private BigDecimal price;
	
	@XmlElementRef(name ="comment",namespace = "http://www.sagar.com/jaxb",required = false )
	private JAXBElement<String> comment;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public JAXBElement<String> getComment() {
		return comment;
	}

	public void setComment(JAXBElement<String> comment) {
		this.comment = comment;
	}


}
