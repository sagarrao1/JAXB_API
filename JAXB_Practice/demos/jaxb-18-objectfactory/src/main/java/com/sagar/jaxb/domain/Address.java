package com.sagar.jaxb.domain;

import javax.xml.bind.annotation.XmlElement;

public class Address {

	@XmlElement(required = true)
	private String street;
	@XmlElement(required = true)
	private String city;
	@XmlElement(required = true)
	private String postalCode;
	
	private Country country;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", postalCode=" + postalCode + ", country=" + country
				+ "]";
	}

	
}