package com.xushi.service;

import java.util.List;

import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Video_source;


public interface Video_sourceService {
	Page<Video_source> findVideo_sourcePage(PageRequest pr);
	Video_source getVideo_source(Integer id);
	void saveVideo_source(Video_source video_source);
	void deleteVideo_source(Video_source video_source);
	List<Video_source> findVideo_source(Integer status);
}
