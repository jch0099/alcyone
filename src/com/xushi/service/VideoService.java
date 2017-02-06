package com.xushi.service;

import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.User;
import com.xushi.entity.Video;
import com.xushi.entity.Video_user;


public interface VideoService {
	Video getVideo(Integer id);
	
	void saveVideo(Video video);

	Page<Video> findVideoPage(String keyword, Integer is_free,Integer status, PageRequest pr);

	void deleteVideo(Video video);

	boolean checkVideo(User user, Video video);
	
	Video_user getVideo_user(Integer user_id,Integer video_id);
	void saveVideo_user(Video_user video_user);
}
