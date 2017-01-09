package com.xushi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.DaoException;
import com.xushi.dao.OrderDao;
import com.xushi.entity.Order;
import com.xushi.service.OrderService;
import com.xushi.util.NumberUtil;
import com.xushi.util.date.DateTimeUtil;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired OrderDao orderDao;

	@Override
	public void saveOrder(Order order) {
		Order temp = orderDao.getBy("order_num", order.getOrder_num());
		if( null != temp && NumberUtil.toInt(temp.getId()) != NumberUtil.toInt(order.getId()) ) throw new DaoException("订单号不能重复!");
		if( order.getId() == null ) {
			order.setCreate_time(DateTimeUtil.getCurDateTime());
			orderDao.save(order);
		}else {
			orderDao.update(order);
		}
	}

	@Override
	public Order getOrder(String order_num) {
		return orderDao.getBy("order_num", order_num);
	}
}
