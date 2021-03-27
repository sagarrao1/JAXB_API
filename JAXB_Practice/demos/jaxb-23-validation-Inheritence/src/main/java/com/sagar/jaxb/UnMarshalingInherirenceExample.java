package com.sagar.jaxb;

import java.io.File;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.sagar.jaxb.domain.inheritence.Address;
import com.sagar.jaxb.domain.inheritence.BillingAddress;
import com.sagar.jaxb.domain.inheritence.PurchaseOrder;
import com.sagar.jaxb.domain.inheritence.ShippingAddress;

public class UnMarshalingInherirenceExample {

	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, SAXException {	
		
		JAXBContext context= JAXBContext.newInstance("com.sagar.jaxb.domain.inheritence");
		Unmarshaller unmarshaller = context.createUnmarshaller();	

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("purchaseOrderInheritence.xsd"));
		
//		unmarshaller.setSchema(schema);
//		marshaller.setEventHandler(new ExampleValidationEventHandler());
		
		PurchaseOrder purchaseOrderInherited = (PurchaseOrder) unmarshaller.unmarshal(new File("purchaseOrderInheritence.xml"));

		System.out.println(purchaseOrderInherited.getCustomer().getName());
		
		
		List<Address> address = purchaseOrderInherited.getCustomer().getAddress();
		
		for (Address addrs : address) {
			
			if (addrs instanceof ShippingAddress) {
				System.out.printf("ShippingAddress : %s , %s, %s ,%s%n", 
						addrs.getStreet(),addrs.getCity(),addrs.getCountry(),addrs.getPostalCode());
			} else if ( addrs instanceof BillingAddress) {
				System.out.printf("BillingAddress : %s , %s, %s ,%s%n", 
						addrs.getStreet(),addrs.getCity(),addrs.getCountry(),addrs.getPostalCode());
			}
		}
		
	}
	
	
	
	
}
