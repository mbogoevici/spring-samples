package org.springsource.stocktrader.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	
	private List<Order> orderList = new ArrayList<Order>();
	
	public OrdersController() {
		createOrders();
	}
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Order> list()
	{			
		return orderList;
	}


	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public Order getNewForm() {
		return new Order();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(Order order) {
		orderList.add(order);
		return "redirect:/orders/";
	}
	
	private void createOrders() {
		Order o = new Order();
		o.setSymbol("CSCO");
		o.setOrderStatus(1);
		o.setOrderType(1);
		o.setPrice(new BigDecimal(22.65));
		o.setQuantity(100); 
		orderList.add(o);
	}
	
}
