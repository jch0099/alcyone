package com.xushi.service;

import com.xushi.entity.Video;


public interface VideoService {
	Video getVideo(Integer id);
	
	void saveVideo(Video video);
}
