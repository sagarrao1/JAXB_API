package com.sagar.jaxb;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sagar.jaxb.domain.Address;
import com.sagar.jaxb.domain.Country;
import com.sagar.jaxb.domain.Customer;
import com.sagar.jaxb.domain.Item;
import com.sagar.jaxb.domain.PurchaseOrder;

public class UnmarshalExample {

	public static void main(String[] args) throws JAXBException {

		// Create the JAXB context
		JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);

		// Create a marshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();

		// write to a file
		File file = new File("PurchaseOrder.xml");
		PurchaseOrder result = (PurchaseOrder) unmarshaller.unmarshal(file);
		
		System.out.println("Customer loyalty: " +result.getCustomer().getLoyalty());
		System.out.println("PurchaseOrder comments: " +result.getComment());
		
	}
		

}
