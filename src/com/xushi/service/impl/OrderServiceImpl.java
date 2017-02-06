package com.xushi.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.DaoException;
import com.xushi.core.dao.QueryWhere;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.dao.OrderDao;
import com.xushi.dao.UserDao;
import com.xushi.entity.Order;
import com.xushi.entity.User;
import com.xushi.entity.Video;
import com.xushi.entity.Video_user;
import com.xushi.service.OrderService;
import com.xushi.service.VideoService;
import com.xushi.util.NumberUtil;
import com.xushi.util.StringUtil;
import com.xushi.util.date.DateTimeUtil;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired OrderDao orderDao;
	@Autowired UserDao userDao;
	@Autowired VideoService videoService;

	@Override
	@Transactional
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

	@Override
	public Order getOrder(Integer id) {
		return orderDao.getById(id);
	}

	@Override
	@Transactional
	public void payOrder(Order order) {
		order.setUpdate_time(DateTimeUtil.getCurDateTime());
		orderDao.update(order);
		if ( order.getType() == 3 ) {//打赏
			return;
		}
		User user = userDao.getById(order.getUser_id());
		if( order.getType() == 1 ) {//买单个视频
			Video_user vu = videoService.getVideo_user(order.getUser_id(), order.getVideo_id());
			if( null == vu ) {
				vu = new Video_user();
				vu.setUser(user);
				vu.setVideo(videoService.getVideo(order.getVideo_id()));
			}
			vu.setEnd_date("9999-99-99");
			videoService.saveVideo_user(vu);
		}else {//买会员
			String end_date = DateTimeUtil.formatDate(DateTimeUtil.addMonth(new Date(), order.getMonth_length()));
			if( !StringUtil.isEmpty(user.getEnd_date()) ){
				end_date = DateTimeUtil.formatDate(DateTimeUtil.addMonth(DateTimeUtil.getDate(user.getEnd_date()), order.getMonth_length()));
			}
			PageRequest pr = new PageRequest(0, Integer.MAX_VALUE);
			List<Video> videos = videoService.findVideoPage("", 2,null, pr).getResults();
			if( null != videos ) for (Video video : videos) {
				Video_user vu = videoService.getVideo_user(order.getUser_id(), video.getId());
				if( null == vu ) {
					vu = new Video_user();
					vu.setUser(user);
					vu.setVideo(videoService.getVideo(video.getId()));
				}
				if( end_date.compareTo(StringUtil.toString(vu.getEnd_date())) > 0 ) vu.setEnd_date(end_date);
				videoService.saveVideo_user(vu);
			}
			user.setEnd_date(end_date);
			userDao.update(user);
		}
	}

	@Override
	public Page<Order> findOrder(Integer status, String start_date,
			String end_date, PageRequest pr) {
		QueryWhere where = new QueryWhere();
		if( null != status ) where.and("status", status);
		if( !StringUtil.isEmpty(start_date) && !StringUtil.isEmpty(end_date) ) {
			where.and("create_time", ">=" , start_date);
			where.and("create_time", "<=" , end_date);
		}
		return orderDao.findByWhere(where, pr);
	}
}
