//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.27 at 07:20:02 AM IST 
//


package com.sagar.jaxb.domain.inheritence;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for loyalty.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="loyalty">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BRONZE"/>
 *     &lt;enumeration value="SILVER"/>
 *     &lt;enumeration value="GOLD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "loyalty")
@XmlEnum
public enum Loyalty {

    BRONZE,
    SILVER,
    GOLD;

    public String value() {
        return name();
    }

    public static Loyalty fromValue(String v) {
        return valueOf(v);
    }

}
