package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.QueryWhere;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.dao.AdvertisementDao;
import com.xushi.entity.Advertisement;
import com.xushi.service.AdvertisementService;
import com.xushi.util.date.DateTimeUtil;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{
	
	@Autowired AdvertisementDao advertisementDao;

	@Override
	public Page<Advertisement> findAdvertisementPage(PageRequest pr) {
		return advertisementDao.findAll(pr);
	}

	@Override
	public Advertisement getAdvertisement(Integer id) {
		return advertisementDao.getById(id);
	}

	@Override
	@Transactional
	public void saveAdvertisement(Advertisement advertisement) {
		advertisement.setUpdate_time(DateTimeUtil.getCurDateTime());
		if( advertisement.getId() == null ) {
			advertisementDao.save(advertisement);
		}else {
			advertisementDao.update(advertisement);
		}
	}

	@Override
	public List<Advertisement> findAdvertisement(Integer page, Integer status) {
		QueryWhere where = new QueryWhere();
		if( null != page ) where.and("page", page);
		if( null != status ) where.and("status", status);
		return advertisementDao.findByWhere(where);
	}

}
