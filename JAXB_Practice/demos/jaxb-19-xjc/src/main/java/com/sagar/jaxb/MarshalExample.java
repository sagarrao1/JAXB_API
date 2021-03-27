package com.sagar.jaxb;

import java.io.File;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sagar.jaxb.domain.Address;
import com.sagar.jaxb.domain.Customer;
import com.sagar.jaxb.domain.Item;
import com.sagar.jaxb.domain.Loyalty;
import com.sagar.jaxb.domain.ObjectFactory;
import com.sagar.jaxb.domain.PurchaseOrder;

public class MarshalExample {

	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {
		PurchaseOrder purchaseOrder = createPurchaseOrder();

		// Create the JAXB context
//		JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);		
//		JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);
		JAXBContext context = JAXBContext.newInstance("com.sagar.jaxb.domain");

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
	
	private static PurchaseOrder createPurchaseOrder() throws DatatypeConfigurationException {
		
		ObjectFactory objectFactory= new ObjectFactory();
		
//		creatin purchaseOrder from class or using objectFactory to create new class is same
//		Onject factory also we are just create new purchaseOrder and returning object
//		PurchaseOrder purchaseOrder = new PurchaseOrder();
		PurchaseOrder purchaseOrder = objectFactory.createPurchaseOrder();

		DatatypeFactory datatypeFactory= DatatypeFactory.newInstance();
		
		XMLGregorianCalendar calendar = datatypeFactory.newXMLGregorianCalendar(new GregorianCalendar());
		purchaseOrder.setOrderDate(calendar);

		Item item1 = objectFactory.createItem();
		item1.setProductName("Ballpoint Pen");
		item1.setQuantity(20);
		item1.setPrice(new BigDecimal("8.95"));
//		item1.setComment("Blue ink");
//        item1.setComment(new JAXBElement<>(new QName("http://www.jesperdj.com/ps-jaxb", "comment"), 
//		String.class, Item.class, "Blue ink"));

		
//		we need to use Objectfactory createItemcomment method to set commenet because we made 
//		comment as JAXElement<String> Object rather String to differential nill =true or empty object in xml
  		item1.setComment("Blue ink");

		Item item2 = objectFactory.createItem();

		item2.setProductName("Pencil");
		item2.setQuantity(10);
		item2.setPrice(new BigDecimal("2.95"));
//		item2.setComment(null);
  		item2.setComment(null);

  		PurchaseOrder.Items items= objectFactory.createPurchaseOrderItems();
  		items.getItem().add(item1);
  		items.getItem().add(item2);
		
		purchaseOrder.setItems(items);

		Customer customer = new Customer();
		customer.setName("John Doe");

		Address address = objectFactory.createAddress();
		address.setStreet("123 Main Street");
		address.setCity("Exampleville");
		address.setPostalCode("12345");
		address.setCountry("India");

		customer.setShipToAddress(address);
		customer.setBillToAddress(address);
		customer.setLoyalty(Loyalty.GOLD);

		purchaseOrder.setCustomer(customer);

		return purchaseOrder;
	}

}
