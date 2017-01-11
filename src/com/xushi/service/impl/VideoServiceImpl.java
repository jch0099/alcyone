package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.QueryWhere;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.dao.VideoDao;
import com.xushi.dao.Video_userDao;
import com.xushi.entity.Video;
import com.xushi.entity.Video_user;
import com.xushi.service.VideoService;
import com.xushi.util.NumberUtil;
import com.xushi.util.SqlHelper;
import com.xushi.util.StringUtil;
import com.xushi.util.date.DateTimeUtil;

@Service
public class VideoServiceImpl implements VideoService{

	@Autowired VideoDao videoDao;
	@Autowired Video_userDao video_userDao;
	
	@Override
	public Video getVideo(Integer id) {
		return videoDao.getById(id);
	}
	
	@Override
	@Transactional
	public void saveVideo(Video video) {
		String now = DateTimeUtil.getCurDateTime();
		video.setUpdate_time(now);
		if( video.getId() == null ) {
			video.setCreate_time(now);
			videoDao.save(video);
		}else {
			videoDao.update(video);
		}
	}

	@Override
	public Page<Video> findVideoPage(String keyword, Integer is_free,PageRequest pr) {
		QueryWhere where = new QueryWhere();
		/*QueryWhere orwhere = new QueryWhere();
		orwhere.orLikeAll("title", SqlHelper.toJpqlParamLike(keyword));
		orwhere.orLikeAll("title", SqlHelper.toJpqlParamLike(keyword));*/
		if( !StringUtil.isEmpty(keyword) ) where.andLikeAll("title", SqlHelper.toJpqlParamLike(keyword));
		if( NumberUtil.toInt(is_free) > 0 ) where.and("is_free", is_free);
		return videoDao.findByWhere(where, pr);
	}

	@Override
	@Transactional
	public void deleteVideo(Video video) {
		QueryWhere where = new QueryWhere();
		where.and("video", video);
		List<Video_user> list = video_userDao.findByWhere(where);
		if( null != list ) video_userDao.delete(list);
		videoDao.delete(video);
	}

}
