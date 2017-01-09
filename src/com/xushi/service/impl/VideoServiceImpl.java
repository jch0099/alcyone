package com.xushi.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.dao.VideoDao;
import com.xushi.dao.Video_userDao;
import com.xushi.entity.Video;
import com.xushi.service.VideoService;
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

}
