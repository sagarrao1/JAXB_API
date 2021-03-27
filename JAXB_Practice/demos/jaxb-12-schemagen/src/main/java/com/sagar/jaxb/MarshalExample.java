package com.sagar.jaxb;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sagar.jaxb.domain.Address;
import com.sagar.jaxb.domain.Country;
import com.sagar.jaxb.domain.Customer;
import com.sagar.jaxb.domain.Item;
import com.sagar.jaxb.domain.Loyalty;
import com.sagar.jaxb.domain.PurchaseOrder;

public class MarshalExample {

	public static void main(String[] args) throws JAXBException {
		PurchaseOrder purchaseOrder = createPurchaseOrder();

		// Create the JAXB context
		JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

		// Create a marshaller
		Marshaller marshaller = context.createMarshaller();

		// To make the XML easier to read for humans, specify that we want it to be
		// formatted
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(purchaseOrder,System.out);
		
		// write to a file
		File file = new File("PurchaseOrder.xml");
		marshaller.marshal(purchaseOrder, file);
		
	}
		
	
	

	private static PurchaseOrder createPurchaseOrder() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();

		purchaseOrder.setOrderDate(new Date());
		Item item1 = new Item();

		item1.setProductName("Ballpoint Pen");
		item1.setQuantity(20);
		item1.setPrice(new BigDecimal("8.95"));
		item1.setComment("Blue ink");		

		Item item2 = new Item();

		item2.setProductName("Pencil");
		item2.setQuantity(10);
		item2.setPrice(new BigDecimal("2.95"));

		purchaseOrder.setItems(Arrays.asList(item1, item2));

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

		purchaseOrder.setCustomer(customer);

		return purchaseOrder;
	}

}
