package com.sagar.jaxb;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sagar.jaxb.domain.PurchaseOrder;

public class UnMarshallingExample2 {

	public static void main(String[] args) throws JAXBException, ParserConfigurationException, SAXException, IOException {

		File file =  new File("src//main//resources//purchaseOrder.xml");
		
		// Create the JAXB context
		JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

		// Create a marshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		// UnMarshal XML using DOM Node
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = documentBuilder.parse(file);
			
		JAXBElement<PurchaseOrder> rootElement = unmarshaller.unmarshal(document, PurchaseOrder.class);
		PurchaseOrder purchaseOrder1 = rootElement.getValue();
		System.out.println(purchaseOrder1.getCustomer().getName());
		System.out.println(purchaseOrder1.getCustomer().getBillingAddress().toString());

	}



}
