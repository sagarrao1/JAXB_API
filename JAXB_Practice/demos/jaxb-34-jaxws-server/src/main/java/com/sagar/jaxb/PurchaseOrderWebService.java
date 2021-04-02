package com.sagar.jaxb;

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
}
