package com.pizzaplace.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaplace.business.bean.BillRangeBean;
import com.pizzaplace.business.bean.PizzaOrderBean;
import com.pizzaplace.service.PizzaService;

@Controller
public class ReportController {
	@Autowired
	private PizzaService pizzaService;
	
	@RequestMapping(value = "/loadDateRangeBillReports", method = RequestMethod.GET)
	public ModelAndView loadDateRangeReportPage() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("OrderReport");
		modelAndView.addObject("bRange", new BillRangeBean());
		return modelAndView;
	}
	@RequestMapping(value = "/fetchOrderDetails", method = RequestMethod.POST)
	public ModelAndView getOrderDetails(@ModelAttribute("bRange")BillRangeBean billRangeBean) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<PizzaOrderBean> pizzaOrderBean = pizzaService.getOrderDetails(billRangeBean.getFromPrice(), billRangeBean.getToPrice());
		modelAndView.setViewName("OrderReport");
		modelAndView.addObject("pizzaOrderList", pizzaOrderBean);
		return modelAndView;
	}
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleAllExceptions(Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		modelAndView.addObject("message", exception.getMessage());
		return modelAndView;
	}
}
