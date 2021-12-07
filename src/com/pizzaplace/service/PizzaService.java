package com.pizzaplace.service;

import java.util.List;
import java.util.Map;
import com.pizzaplace.business.bean.PizzaOrderBean;

public interface PizzaService {

	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill) throws Exception;
	
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzOrderBean) throws Exception;
	
	public Map<Integer, String> findAllPizzaDetails() throws Exception;
	
	
}
