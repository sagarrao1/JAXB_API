package com.sagar.jaxb;

import java.io.File;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.sagar.jaxb.domain.inv.ProductStock;
import com.sagar.jaxb.domain.po.PurchaseOrder;

public class UnmarshalExampleforPOandInvXSDs {

	public static void main(String[] args) throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance("com.sagar.jaxb.domain.po:com.sagar.jaxb.domain.inv");
			Unmarshaller unmarshaller = context.createUnmarshaller();
		
			SchemaFactory schemaFactory =SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("purchaseOrder.xsd"));
//			
			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new ExampleValidationEventHandler());
			
			PurchaseOrder purchaseOrder = (PurchaseOrder) unmarshaller.unmarshal(new File("purchaseOrder.xml"));
			
			System.out.println("Customer name : " +purchaseOrder.getCustomer().getName());
			
			List<Object> anyObjects = purchaseOrder.getExtra().getAny();
			
			for (Object object : anyObjects) {
				if (object instanceof Element) {
					Element element=  (Element) object;
					System.out.println("Extra information as DOM element: " +element.getNodeName());
				} else if (object instanceof ProductStock)  {					
					ProductStock procductStock= (ProductStock) object;					
					System.out.printf("Extra information of type : %s, %d, %s%n ",
							procductStock.getProductName(), procductStock.getStock() ,
							procductStock.getDate());
					
					
				}				
			}
	}
}
