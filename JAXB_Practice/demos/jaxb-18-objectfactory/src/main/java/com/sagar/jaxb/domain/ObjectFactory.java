package com.sagar.jaxb.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
	
	@XmlElementDecl(name ="comment",namespace = "http://www.sagar.com/jaxb", scope = Item.class)
	public JAXBElement<String> createItemComment(String comment) {				
		return new JAXBElement<String>(new QName("http://www.sagar.com/jaxb", "comment"), 
				String.class, Item.class, comment);
	}

}
