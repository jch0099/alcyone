package com.xushi.service;

import java.util.List;

import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Image;


public interface ImageService {
	Page<Image> findImagePage(PageRequest pr);
	Image getImage(Integer id);
	void saveImage(Image image);
	void deleteImage(Image image);
	List<Image> findImage(Integer status);
}
