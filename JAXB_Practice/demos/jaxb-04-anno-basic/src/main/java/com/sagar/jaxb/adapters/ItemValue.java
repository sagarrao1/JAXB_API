package com.sagar.jaxb.adapters;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.sagar.jaxb.domain.Item;

@XmlType(propOrder = {"productName", "quantity", "price", "comment"})
public class ItemValue {

	private String productCode;
	private Item item;
	
//	Constructors
	
	public ItemValue(Item item) {
		super();
		this.item = item;
	}

	public ItemValue(String productCode, Item item) {
		super();
		this.productCode = productCode;
		this.item = item;
	}

	@XmlAttribute
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

///

	public String getProductName() {
		return item.getProductName();
	}

	public void setProductName(String productName) {
		item.setProductName(productName);
	}

	public int getQuantity() {
		return item.getQuantity();
	}

	public void setQuantity(int quantity) {
		item.setQuantity(quantity);
	}

	public BigDecimal getPrice() {
		return item.getPrice();
	}

	public void setPrice(BigDecimal price) {
		item.setPrice(price);
	}

	public String getComment() {
		return item.getComment();
	}

	public void setComment(String comment) {
		item.setComment(comment);
	}

	public Item toItem() {
		return item;
	}

}
