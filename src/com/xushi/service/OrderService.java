package com.xushi.service;

import com.xushi.entity.Order;


public interface OrderService {
	void saveOrder(Order order);
	Order getOrder(String order_num);
}
