package com.sagar.jaxb;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sagar.jaxb.domain.Address;
import com.sagar.jaxb.domain.Customer;
import com.sagar.jaxb.domain.Item;
import com.sagar.jaxb.domain.Loyalty;
import com.sagar.jaxb.domain.PurchaseOrder;

public class MarshallingExample2 {

	public static void main(String[] args) throws JAXBException, XMLStreamException, FactoryConfigurationError {
		PurchaseOrder purchaseOrder = createPurchaseOrder();

		// Create the JAXB context
		JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

		// Create a marshaller
		Marshaller marshaller = context.createMarshaller();

		// To make the XML easier to read for humans, specify that we want it to be
		// formatted
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

		QName rootElementName = new QName(null, "purchaseOrder");
		JAXBElement<PurchaseOrder> rootElement = new JAXBElement<>(rootElementName, PurchaseOrder.class, purchaseOrder);
		
		
		// User  StaX API to create XML document
		XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
		
		writer.writeStartDocument("UTF-8", "1.0");
        writer.writeStartElement("orders");
        
		// Marshal and output to the console
		marshaller.marshal(rootElement, writer);

        writer.writeEndElement();
        writer.writeEndDocument();
        writer.close();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static PurchaseOrder createPurchaseOrder() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();

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

		purchaseOrder.setItems(Arrays.asList(item1, item2));

		Customer customer = new Customer();
		customer.setName("John Doe");

		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("Exampleville");
		address.setPostalCode("12345");
		address.setCountry("USA");

		customer.setShippingAddress(address);
		customer.setBillingAddress(address);
		customer.setLoyalty(Loyalty.GOLD);

		purchaseOrder.setCustomer(customer);

		return purchaseOrder;
	}

}
