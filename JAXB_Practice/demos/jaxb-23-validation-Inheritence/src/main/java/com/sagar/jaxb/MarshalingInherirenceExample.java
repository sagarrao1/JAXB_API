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

import com.sagar.jaxb.domain.inheritence.Address;
import com.sagar.jaxb.domain.inheritence.BillingAddress;
import com.sagar.jaxb.domain.inheritence.Customer;
import com.sagar.jaxb.domain.inheritence.Item;
import com.sagar.jaxb.domain.inheritence.Loyalty;
import com.sagar.jaxb.domain.inheritence.PurchaseOrder;
import com.sagar.jaxb.domain.inheritence.ShippingAddress;
import com.sagar.jaxb.domain.inheritence.PurchaseOrder.Items;

public class MarshalingInherirenceExample {

	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, SAXException {	
		
		PurchaseOrder purchaseOrder = createPurchaseOrder();
		
		JAXBContext context= JAXBContext.newInstance("com.sagar.jaxb.domain.inheritence");
		Marshaller marshaller = context.createMarshaller();		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("purchaseOrderInheritence.xsd"));
		
		marshaller.setSchema(schema);
//		marshaller.setEventHandler(new ExampleValidationEventHandler());

		marshaller.marshal(purchaseOrder, System.out);
		marshaller.marshal(purchaseOrder, new File("purchaseOrderInheritence.xml"));
	
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
		
		
		ShippingAddress shippingAddress= new ShippingAddress();		
		shippingAddress.setStreet("Madeenaguda");
		shippingAddress.setCity("Hyderabad");
		shippingAddress.setCountry("India");
		shippingAddress.setPostalCode("500049");
		
		BillingAddress billingAddress= new BillingAddress();		
		billingAddress.setStreet("Nizampet");
		billingAddress.setCity("Hyderabad");
		billingAddress.setCountry("India");
		billingAddress.setPostalCode("50090");
		
		Customer cust= new Customer();
		cust.setName("Sagar");
		cust.setLoyalty(Loyalty.GOLD);	

		cust.getAddress().add(shippingAddress);
		cust.getAddress().add(billingAddress);
		
		purchaseOrder.setCustomer(cust);
		
		
		return purchaseOrder;
	}
	
	
}
