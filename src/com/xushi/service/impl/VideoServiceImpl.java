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
import com.xushi.entity.User;
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
	public Page<Video> findVideoPage(String keyword, Integer is_free,Integer status,PageRequest pr) {
		QueryWhere where = new QueryWhere();
		/*QueryWhere orwhere = new QueryWhere();
		orwhere.orLikeAll("title", SqlHelper.toJpqlParamLike(keyword));
		orwhere.orLikeAll("title", SqlHelper.toJpqlParamLike(keyword));*/
		if( !StringUtil.isEmpty(keyword) ) where.andLikeAll("title", SqlHelper.toJpqlParamLike(keyword));
		if( NumberUtil.toInt(is_free) > 0 ) where.and("is_free", is_free);
		if( NumberUtil.toInt(status) > 0 ) where.and("status", status);
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

	@Override
	public boolean checkVideo(User user, Video video) {
		if( null == video ) return false;
		if( video.getIs_free() == 1 ) return true;
		if( null == user ) return false;
		QueryWhere where = new QueryWhere();
		where.and("user", user);
		where.and("video", video);
		Video_user vu = video_userDao.getByWhere(where);
		if( null == vu ) return false;
		String now = DateTimeUtil.getCurDate();
		if(null== vu.getEnd_date()) return true;
		if ( now.compareTo(vu.getEnd_date()) > 0 ) return false;
		return true;
	}

	@Override
	public Video_user getVideo_user(Integer user_id, Integer video_id) {
		QueryWhere where = new QueryWhere();
		where.and("user.id", user_id);
		where.and("video.id", video_id);
		Video_user vu = video_userDao.getByWhere(where);
		return vu;
	}

	@Override
	@Transactional
	public void saveVideo_user(Video_user video_user) {
		if( video_user.getId() == null ) {
			video_userDao.save(video_user);
		}else {
			video_userDao.update(video_user);
		}
	}

}
