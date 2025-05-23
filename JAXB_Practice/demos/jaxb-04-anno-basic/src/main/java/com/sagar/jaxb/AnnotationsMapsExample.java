package com.sagar.jaxb;

import java.awt.ItemSelectable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import com.sagar.jaxb.domain.Address;
import com.sagar.jaxb.domain.Country;
import com.sagar.jaxb.domain.Customer;
import com.sagar.jaxb.domain.Item;
import com.sagar.jaxb.domain.Loyalty;
import com.sagar.jaxb.domain.PurchaseOrder;
import com.sagar.jaxb.domain.PurchaseOrderMap;

public class AnnotationsMapsExample {

	public static void main(String[] args) throws JAXBException {
		PurchaseOrderMap purchaseOrderMap = createPurchaseOrderMap();

		// Create the JAXB context
		JAXBContext context = JAXBContext.newInstance(PurchaseOrderMap.class);

		// Create a marshaller
		Marshaller marshaller = context.createMarshaller();

		// To make the XML easier to read for humans, specify that we want it to be
		// formatted
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

//		Create a JAXB element wrapper
//		1st way by using JAXBElement
//		QName rootElementName = new QName(null, "purchaseOrder");
//		JAXBElement<PurchaseOrder> rootElement = new JAXBElement<>(rootElementName, PurchaseOrder.class, purchaseOrder);
		
		// Marshal and output to the console
//		marshaller.marshal(rootElement, System.out);

//		2nd way not using above JAXBElement
		marshaller.marshal(purchaseOrderMap,System.out);
	}
	
	
	

	private static PurchaseOrderMap createPurchaseOrderMap() {
		PurchaseOrderMap purchaseOrderMap = new PurchaseOrderMap();

		purchaseOrderMap.setOrderDate(new Date());
		Item item1 = new Item();

		item1.setProductName("Ballpoint Pen");
		item1.setQuantity(20);
		item1.setPrice(new BigDecimal("8.95"));
		item1.setComment("Blue ink");

		Item item2 = new Item();

		item2.setProductName("Pencil");
		item2.setQuantity(10);
		item2.setPrice(new BigDecimal("2.95"));
		item2.setComment("Blue ink");
		
		Map<String , Item> items= new HashMap<String, Item>();
		items.put("1234", item1);
		items.put("4567", item2);
		
		purchaseOrderMap.setItems(items);

		Customer customer = new Customer();
		customer.setName("John Doe");
		
		Country ctry=  new Country();
		
		ctry.setCode("IN");
		ctry.setName("India");

		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("Exampleville");
		address.setPostalCode("12345");
		address.setCountry(ctry);

		customer.setShippingAddress(address);
		customer.setBillingAddress(address);
		customer.setLoyalty(Loyalty.GOLD);

		purchaseOrderMap.setCustomer(customer);

		return purchaseOrderMap;
	}

}
