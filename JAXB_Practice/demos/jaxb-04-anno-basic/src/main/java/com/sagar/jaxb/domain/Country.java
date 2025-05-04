package com.sagar.jaxb.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

//We can use @XmlValue only once per class as it maps to simple type in xml
// but we can use xml attribute for other column and  with with @xmlvalue with other column as it maps 
// to simple type attribute to same element like it shows below in xml
// <country code="IN">India</country>

public class Country {

//	@XmlAttribute
	@XmlValue
	private String code;
	
//	@XmlValue
	@XmlTransient
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
