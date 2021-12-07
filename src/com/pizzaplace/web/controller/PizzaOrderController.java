package com.pizzaplace.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaplace.business.bean.PizzaOrderBean;
import com.pizzaplace.service.PizzaService;

@Controller
public class PizzaOrderController {
	
	@Autowired
	private PizzaService pizzaService;
	
	
	@RequestMapping(value = "/loadPizzaOrder", method = RequestMethod.GET)
	public ModelAndView loadSavePizza() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("PizzaOrder");
		modelAndView.addObject("piz", new PizzaOrderBean());
		return modelAndView;
	}
	@RequestMapping(value = "/savePizzaOrder", method = RequestMethod.POST)
	public ModelAndView addPizzaOrderDetails(@Valid @ModelAttribute("piz")PizzaOrderBean pizzaOrderBean, BindingResult result) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		if(result.hasErrors()) {
			modelAndView.setViewName("PizzaOrder");
		}else {
			System.out.println("in here");
			PizzaOrderBean orderBean = pizzaService.addPizzaOrderDetails(pizzaOrderBean);
			Integer orderId = orderBean.getOrderId();
			Double bill = orderBean.getBill();
			String customerName = orderBean.getCustomerName();
			modelAndView.setViewName("PizzaOrderSuccess");
			modelAndView.addObject("message", "Hi: "+customerName+", your order is placed with orderId: "+orderId+", Bill to be paid is: "+bill);
			System.out.println(modelAndView);
		}
		return modelAndView;
	}
	@ModelAttribute("pizzaIdAndName")
	public Map<Integer, String> populatePizzaNames() throws Exception{
		Map<Integer, String> pizzaMap = pizzaService.findAllPizzaDetails();
		return pizzaMap;
	}
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleAllExceptions(Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		modelAndView.addObject("message", exception.getMessage());
		return modelAndView;
	}
	
}
