package com.sagar.jaxb;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.sagar.jaxb.domain.Address;
import com.sagar.jaxb.domain.Customer;
import com.sagar.jaxb.domain.Item;
import com.sagar.jaxb.domain.Loyalty;
import com.sagar.jaxb.domain.ObjectFactory;
import com.sagar.jaxb.domain.PurchaseOrder;
import com.sagar.jaxb.domain.PurchaseOrder.Items;
import com.sagar.jaxb.domain.PurchaseOrderService;
import com.sagar.jaxb.domain.PurchaseOrders;

public class PurchaseOrderClient {

	public static void main(String[] args) throws DatatypeConfigurationException {
		PurchaseOrderService service= new PurchaseOrderService();
		PurchaseOrders purchaseOrderPort = service.getPurchaseOrderPort();

		Long id = purchaseOrderPort.addOrder(createPurchaseOrder());
		
		System.out.println("Order created with id: "+id);
		
		List<PurchaseOrder> ordersStored = purchaseOrderPort.getOrders();
		
		for (PurchaseOrder purchaseOrder : ordersStored) {
			System.out.println("Purchase Order Id : "+purchaseOrder.getId());
			System.out.println("No. of Items in order : "+purchaseOrder.getItems().getItem().size());
			for (Item item : purchaseOrder.getItems().getItem()) {
				System.out.println("product name : "+item.getProductName()+
						", Quantty: "+item.getQuantity() +", price: "+item.getPrice() +", item comment :"+item.getComment());
			}		
			
			System.out.println();
			System.out.println("Customer : " +purchaseOrder.getCustomer().getName());
			System.out.println("BillToAddress : " +purchaseOrder.getCustomer().getBillToAddress().getStreet() + ", "
					+ purchaseOrder.getCustomer().getBillToAddress().getCity() +" , "
					+ purchaseOrder.getCustomer().getBillToAddress().getCountry() +" , "
					+ purchaseOrder.getCustomer().getBillToAddress().getPostalCode() 
					);
			System.out.println("ShipToAddress : " +purchaseOrder.getCustomer().getShipToAddress().getStreet() + ", "
					+ purchaseOrder.getCustomer().getShipToAddress().getCity() +" , "
					+ purchaseOrder.getCustomer().getShipToAddress().getCountry() +" , "
					+ purchaseOrder.getCustomer().getShipToAddress().getPostalCode() 
					);
			System.out.println("loyalty : "+purchaseOrder.getCustomer().getLoyalty());
			System.out.println();
			System.out.println("comment : " +purchaseOrder.getComment().getValue());
			System.out.println("order Date : " +purchaseOrder.getOrderDate());
			System.out.println("==================================================");
			System.out.println();
		}
		
		System.out.println("*******************************");
		Long getId= (long) 2;
		PurchaseOrder result = (PurchaseOrder) purchaseOrderPort.getOrder(getId);
		System.out.println("Purchase Order Id : "+result.getId());
		System.out.println(result.getCustomer().getName());
		
	}

	private static PurchaseOrder createPurchaseOrder() throws DatatypeConfigurationException {
		ObjectFactory objectFactory= new ObjectFactory();		
		
		PurchaseOrder purchaseOrder = objectFactory.createPurchaseOrder();
		
		Item item1 = objectFactory.createItem();
		
		item1.setProductName("Pen");
		item1.setQuantity(5);
		item1.setPrice(new BigDecimal("23.5"));
		item1.setComment("Ball point pen");
		
		Item item2 = objectFactory.createItem();
		
		item2.setProductName("Pencil");
		item2.setQuantity(4);
		item2.setPrice(new BigDecimal("12.5"));
		item2.setComment("Natraj pencil");
		
		PurchaseOrder.Items items=  new PurchaseOrder.Items();
		items.getItem().add(item1);
		items.getItem().add(item2);
		
		purchaseOrder.setItems(items);
		
		Address address = objectFactory.createAddress();
		address.setStreet("Madinaaguda");
		address.setCity("Hyd");
		address.setCountry("India");
		address.setPostalCode("500049");
		
		Customer customer = objectFactory.createCustomer();
		customer.setName("Sagar");
		customer.setLoyalty(Loyalty.GOLD);
		customer.setBillToAddress(address);
		customer.setShipToAddress(address);
		
		purchaseOrder.setCustomer(customer);
		
		XMLGregorianCalendar calender= DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());		
		System.out.println("calender :"+ calender);
		purchaseOrder.setOrderDate(calender);
		
		JAXBElement<String> jaxbComment=  new JAXBElement<String>(new QName("http://www.sagar.com/jaxb", "comment"), 
				String.class, PurchaseOrder.class, "Sanju I love you..");
		
		purchaseOrder.setComment(jaxbComment);
		
		return purchaseOrder;
	}

}
