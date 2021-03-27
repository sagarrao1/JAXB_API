package com.sagar.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.sagar.jaxb.domain.PurchaseOrder;

public class UnMarshallingExample1 {

	public static void main(String[] args) throws JAXBException {

		File file =  new File("src//main//resources//purchaseOrder.xml");
		
		// Create the JAXB context
		JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

		// Create a marshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		// UnMarshal XML to Java Object
		JAXBElement<PurchaseOrder> rootElement = unmarshaller.unmarshal(new StreamSource(file), PurchaseOrder.class);
		PurchaseOrder purchaseOrder1 = rootElement.getValue();
		System.out.println(purchaseOrder1.getCustomer().getName());
		System.out.println(purchaseOrder1.getCustomer().getBillingAddress().toString());

	}



}
