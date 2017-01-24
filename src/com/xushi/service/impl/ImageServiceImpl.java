package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.QueryWhere;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.dao.ImageDao;
import com.xushi.entity.Image;
import com.xushi.service.ImageService;
import com.xushi.util.date.DateTimeUtil;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired ImageDao imageDao;

	@Override
	public Page<Image> findImagePage(PageRequest pr) {
		return imageDao.findAll(pr);
	}

	@Override
	public Image getImage(Integer id) {
		return imageDao.getById(id);
	}

	@Override
	@Transactional
	public void saveImage(Image image) {
		image.setUpdate_time(DateTimeUtil.getCurDateTime());
		if( image.getId() == null ) {
			image.setCreate_time(DateTimeUtil.getCurDateTime());
			imageDao.save(image);
		}else {
			imageDao.update(image);
		}
	}

	@Override
	@Transactional
	public void deleteImage(Image image) {
		imageDao.delete(image);
	}

	@Override
	public List<Image> findImage(Integer status) {
		QueryWhere where = new QueryWhere();
		if( null != status ) where.and("status", status);
		return imageDao.findByWhere(where);
	}
}
