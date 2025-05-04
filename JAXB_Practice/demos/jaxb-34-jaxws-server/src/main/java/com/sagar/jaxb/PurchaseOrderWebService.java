package com.sagar.jaxb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.sagar.jaxb.domain.Address;
import com.sagar.jaxb.domain.Customer;
import com.sagar.jaxb.domain.Item;
import com.sagar.jaxb.domain.Loyalty;
//import com.sagar.jaxb.domain.Loyalty;
import com.sagar.jaxb.domain.PurchaseOrder;


@WebService(name = "PurchaseOrders",
serviceName = "PurchaseOrderService",
portName = "PurchaseOrderPort",
targetNamespace = "http://www.sagar.com/jaxb")
public class PurchaseOrderWebService {

private final Map<Long, PurchaseOrder>  purchaseOrders= new HashMap<>();

private final AtomicLong ID_GENERATOR = new AtomicLong();

@WebMethod
@WebResult(name = "orders")
public List<PurchaseOrder> getOrders(){
	createPurchaseOrder();	
	return new ArrayList<PurchaseOrder> (purchaseOrders.values());
}

@WebMethod
@WebResult(name = "order")
public PurchaseOrder getOrder(@WebParam(name = "id") Long id) {
	if (purchaseOrders.containsKey(id)) {
		return purchaseOrders.get(id);
	} else {
		throw new IllegalArgumentException("Invalid id");
	}
}

@WebMethod
@WebResult(name = "id")
public Long addOrder(@WebParam(name = "order") PurchaseOrder input) {
//	int size = purchaseOrders.keySet().size();
//	System.out.println("size "+size);
//	purchaseOrders.put((long) (size+1), input);

	long id = ID_GENERATOR.incrementAndGet();
	input.setId(id);
	purchaseOrders.put(id, input);
	return id;
}

@WebMethod
public void removeOrder(@WebParam(name = "id")Long id) {	
	purchaseOrders.remove(id);
	}


public static void main(String[] args) {
	
	Endpoint.publish("http://localhost:8080/po", new PurchaseOrderWebService());
	System.out.println("Running .......");
	
	
}


private void createPurchaseOrder(){			
	
	PurchaseOrder po = new PurchaseOrder();
	
	Item item1 = new Item();
	
	item1.setProductName("Pen");
	item1.setQuantity(5);
	item1.setPrice(new BigDecimal("23.5"));
	item1.setComment("Ball point pen");
	
	Item item2 = new Item();
	
	item2.setProductName("Pencil");
	item2.setQuantity(4);
	item2.setPrice(new BigDecimal("12.5"));
	item2.setComment("Natraj pencil");
	
	List<Item> items=  new ArrayList<>();
	items.add(item1);
	items.add(item2);	
	
	po.setItems(items);
	
	Address address = new Address();
	address.setStreet("Madinaaguda");
	address.setCity("Hyd");
	address.setCountry(null);
	address.setPostalCode("500049");
	
	Customer customer = new Customer();
	customer.setName("Murali");
	customer.setLoyalty(Loyalty.GOLD);
	customer.setBillingAddress(address);
	customer.setShippingAddress(address);
	
	po.setCustomer(customer);
	
//	XMLGregorianCalendar calender= DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());		
//	System.out.println("calender :"+ calender);
	po.setOrderDate(null);
	
	po.setComment("this is PO comment");
	
	long id = ID_GENERATOR.incrementAndGet();
	purchaseOrders.put(id, po);
	
	System.out.println("po id's : ");
	System.out.println(purchaseOrders.keySet());
}
}
