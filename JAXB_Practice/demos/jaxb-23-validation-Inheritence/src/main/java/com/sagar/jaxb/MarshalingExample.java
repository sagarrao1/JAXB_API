package com.sagar.jaxb;

import java.io.File;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.sagar.jaxb.domain.Address;
import com.sagar.jaxb.domain.Customer;
import com.sagar.jaxb.domain.Item;
import com.sagar.jaxb.domain.Loyalty;
import com.sagar.jaxb.domain.PurchaseOrder;
import com.sagar.jaxb.domain.PurchaseOrder.Items;

public class MarshalingExample {

	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, SAXException {	
		
		PurchaseOrder purchaseOrder = createPurchaseOrder();
		
		JAXBContext context= JAXBContext.newInstance("com.sagar.jaxb.domain");
		Marshaller marshaller = context.createMarshaller();		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("purchaseOrder.xsd"));
		
		marshaller.setSchema(schema);
//		marshaller.setEventHandler(new ExampleValidationEventHandler());

		marshaller.marshal(purchaseOrder, System.out);
		marshaller.marshal(purchaseOrder, new File("purchaseOrderGenerated.xml"));
	
	}
	
	
	

	private static PurchaseOrder createPurchaseOrder() throws DatatypeConfigurationException {		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		
		DatatypeFactory datatypeFactory= DatatypeFactory.newInstance();		
		XMLGregorianCalendar calendar=datatypeFactory.newXMLGregorianCalendar(new GregorianCalendar());
		purchaseOrder.setOrderDate(calendar);
		
		Item item1= new Item();
		item1.setProductName("Pen");
		item1.setPrice(new BigDecimal("23.2"));
		item1.setQuantity(4);		
		item1.setComment(new JAXBElement<String>(new QName("http://www.sagar.com/jaxb", "comment"), String.class, Item.class, "Blue ink") );
		
		Item item2= new Item();
		item2.setProductName("Pencil");
		item2.setPrice(new BigDecimal("12.2"));
		item2.setQuantity(7);
		
//		JAXBElement<String> cmt=  new JAXBElement<String>("http://www.sagar.com/jaxb", String.class, comment);
//		item2.setComment(cmt);

		PurchaseOrder.Items items= new Items();
		items.getItem().add(item1);
		items.getItem().add(item2);
		purchaseOrder.setItems(items);
		
		
		Address address= new Address();
		
		address.setStreet("Madeenaguda");
		address.setCity("Hyderabad");
		address.setCountry("India");
		address.setPostalCode("500049");
		
		
		Customer cust= new Customer();
		cust.setName("Sagar");
		cust.setBillingAddress(address);
		cust.setShippingAddress(address);
		cust.setLoyalty(Loyalty.GOLD);	
		
		purchaseOrder.setCustomer(cust);
		
		
		return purchaseOrder;
	}
	
	
}
