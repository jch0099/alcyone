package com.xushi.service;

import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.User;
import com.xushi.entity.Video;


public interface VideoService {
	Video getVideo(Integer id);
	
	void saveVideo(Video video);

	Page<Video> findVideoPage(String keyword, Integer is_free, PageRequest pr);

	void deleteVideo(Video video);

	boolean checkVideo(User user, Video video);
}
