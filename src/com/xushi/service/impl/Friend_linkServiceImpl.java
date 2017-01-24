package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.QueryWhere;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.dao.Friend_linkDao;
import com.xushi.entity.Friend_link;
import com.xushi.service.Friend_linkService;
import com.xushi.util.date.DateTimeUtil;

@Service
public class Friend_linkServiceImpl implements Friend_linkService{
	
	@Autowired Friend_linkDao friend_linkDao;

	@Override
	public Page<Friend_link> findFriend_linkPage(PageRequest pr) {
		return friend_linkDao.findAll(pr);
	}

	@Override
	public Friend_link getFriend_link(Integer id) {
		return friend_linkDao.getById(id);
	}

	@Override
	@Transactional
	public void saveFriend_link(Friend_link friend_link) {
		friend_link.setUpdate_time(DateTimeUtil.getCurDateTime());
		if( friend_link.getId() == null ) {
			friend_link.setCreate_time(DateTimeUtil.getCurDateTime());
			friend_linkDao.save(friend_link);
		}else {
			friend_linkDao.update(friend_link);
		}
	}

	@Override
	@Transactional
	public void deleteFriend_link(Friend_link friend_link) {
		friend_linkDao.delete(friend_link);
	}

	@Override
	public List<Friend_link> findFriend_link(Integer status) {
		QueryWhere where = new QueryWhere();
		if( null != status ) where.and("status", status);
		return friend_linkDao.findByWhere(where);
	}
}
