package com.xushi.service;

import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Order;


public interface OrderService {
	void saveOrder(Order order);
	Order getOrder(String order_num);
	Order getOrder(Integer id);
	void payOrder(Order order);
	
	Page<Order> findOrder(Integer status,String start_date,String end_date,PageRequest pr);
}
