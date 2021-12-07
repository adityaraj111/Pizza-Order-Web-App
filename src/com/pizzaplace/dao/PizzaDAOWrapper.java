package com.pizzaplace.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pizzaplace.business.bean.PizzaBean;
import com.pizzaplace.business.bean.PizzaOrderBean;
import com.pizzaplace.entity.PizzaEntity;
import com.pizzaplace.entity.PizzaOrderEntity;

@Repository
@Transactional(value = "txManager")
public class PizzaDAOWrapper {
	
	@Autowired
	private PizzaDAO pizzaDAO;
	
	@Autowired
	private PizzaOrderDAO pizzaOrderDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<PizzaBean> findAllPizzaDetails() throws Exception{
		List<PizzaBean> resultList = new ArrayList<PizzaBean>();
		try {
			List<PizzaEntity> pizzaEntityList = pizzaDAO.findAllPizzaDetails();
			pizzaEntityList.forEach( (pz) -> {
				PizzaBean pizzaBean = convertPizzaEntityToBean(pz);
				resultList.add(pizzaBean);
				
			});
		} catch (Exception e) {
			throw e;
		}
		return resultList;
	}
	
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzOrderBean) throws Exception{
		PizzaOrderBean resultBean = null;
		PizzaOrderEntity pizzaOrderEntity = convertPizzaOrderBeanToEntity(pizzOrderBean);
		try {
			PizzaOrderEntity pizzaOrderEntity2 = pizzaOrderDAO.save(pizzaOrderEntity);
			resultBean = convertPizzaOrderEntityToBean(pizzaOrderEntity2);
		} catch (Exception e) {
			throw e;
		}
		return resultBean;
	}
	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill) throws Exception{
		List<PizzaOrderBean> resulList = new ArrayList<PizzaOrderBean>();
		try {
			Query query = entityManager.createQuery("select k from PizzaOrderEntity k where k.bill>?1 and k.bill<?2");
			query.setParameter(1, fromBill);
			query.setParameter(2, toBill);
			List<PizzaOrderEntity> pizzaEntityList = query.getResultList();
			pizzaEntityList.forEach( (p)-> {
				PizzaOrderBean orderBean = convertPizzaOrderEntityToBean(p);
				resulList.add(orderBean);
			});
		} catch (Exception e) {
			throw e;
		}
		return resulList;
	}
	public Double getPizzaPrice(Integer pizzaId) throws Exception{
		double price = 0;
		try {
			PizzaEntity pizzaEntity = entityManager.find(PizzaEntity.class, pizzaId);
			if(pizzaEntity != null) {
				price = pizzaEntity.getPrice();
			}
		} catch (Exception e) {
			throw e;
		}
		return price;
	}
	
	// conversion methods
	public static PizzaBean convertPizzaEntityToBean(PizzaEntity entity) {
		PizzaBean bean = new PizzaBean();
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}
	public static PizzaEntity convertPizzaBeanToEntity(PizzaBean bean) {
		PizzaEntity entity = new PizzaEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}
	public static PizzaOrderBean convertPizzaOrderEntityToBean(PizzaOrderEntity entity) {
		PizzaOrderBean bean = new PizzaOrderBean();
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}
	public static PizzaOrderEntity convertPizzaOrderBeanToEntity(PizzaOrderBean bean) {
		PizzaOrderEntity entity = new PizzaOrderEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}
	

}
