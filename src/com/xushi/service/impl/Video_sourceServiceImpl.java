package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.QueryWhere;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.dao.Video_sourceDao;
import com.xushi.entity.Video_source;
import com.xushi.service.Video_sourceService;
import com.xushi.util.date.DateTimeUtil;

@Service
public class Video_sourceServiceImpl implements Video_sourceService{
	
	@Autowired Video_sourceDao video_sourceDao;

	@Override
	public Page<Video_source> findVideo_sourcePage(PageRequest pr) {
		return video_sourceDao.findAll(pr);
	}

	@Override
	public Video_source getVideo_source(Integer id) {
		return video_sourceDao.getById(id);
	}

	@Override
	@Transactional
	public void saveVideo_source(Video_source video_source) {
		video_source.setUpdate_time(DateTimeUtil.getCurDateTime());
		if( video_source.getId() == null ) {
			video_source.setCreate_time(DateTimeUtil.getCurDateTime());
			video_sourceDao.save(video_source);
		}else {
			video_sourceDao.update(video_source);
		}
	}

	@Override
	@Transactional
	public void deleteVideo_source(Video_source video_source) {
		video_sourceDao.delete(video_source);
	}

	@Override
	public List<Video_source> findVideo_source(Integer status) {
		QueryWhere where = new QueryWhere();
		if( null != status ) where.and("status", status);
		return video_sourceDao.findByWhere(where);
	}
}
