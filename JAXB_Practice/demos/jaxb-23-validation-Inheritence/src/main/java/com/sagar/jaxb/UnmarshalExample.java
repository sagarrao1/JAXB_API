package com.sagar.jaxb;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.sagar.jaxb.domain.PurchaseOrder;

public class UnmarshalExample {

	public static void main(String[] args) throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance("com.sagar.jaxb.domain");
			Unmarshaller unmarshaller = context.createUnmarshaller();
		
			SchemaFactory schemaFactory =SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("purchaseOrder.xsd"));
			
			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new ExampleValidationEventHandler());
			
			File file=  new File("src//main//resources//purchaseOrder.xml");
			PurchaseOrder purchaseOrder = (PurchaseOrder) unmarshaller.unmarshal(file);
			
			System.out.println("Customer name : " +purchaseOrder.getCustomer().getName());
	}
}
