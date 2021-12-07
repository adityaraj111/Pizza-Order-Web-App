package com.pizzaplace.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaplace.business.bean.PizzaBean;
import com.pizzaplace.business.bean.PizzaOrderBean;
import com.pizzaplace.dao.PizzaDAOWrapper;
@Service
public class PizzaServiceImpl implements PizzaService{
	@Autowired
	private PizzaDAOWrapper pizzaDAOWrapper;

	@Override
	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill) throws Exception {
		return pizzaDAOWrapper.getOrderDetails(fromBill, toBill);
	}

	@Override
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean) throws Exception {
		double price = pizzaDAOWrapper.getPizzaPrice(pizzaOrderBean.getPizzaId());
		double totalBill = pizzaOrderBean.getNumberOfPiecesOrdered()*price;
		pizzaOrderBean.setBill(totalBill);
		PizzaOrderBean resultOrderBean =  pizzaDAOWrapper.addPizzaOrderDetails(pizzaOrderBean);
		return resultOrderBean;
	}

	@Override
	public Map<Integer, String> findAllPizzaDetails() throws Exception {
		List<PizzaBean> pizzaBeanList =  pizzaDAOWrapper.findAllPizzaDetails();
		Map<Integer, String> pizzaMap = new LinkedHashMap<Integer, String>();
		pizzaBeanList.forEach((pz) -> {
			pizzaMap.put(pz.getPizzaId(), pz.getPizzaName());
		});
		return pizzaMap;
	}

}
