package com.xushi.service;

import java.util.List;

import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Advertisement;


public interface AdvertisementService {
	Page<Advertisement> findAdvertisementPage(PageRequest pr);
	Advertisement getAdvertisement(Integer id);
	void saveAdvertisement(Advertisement advertisement);
	
	List<Advertisement> findAdvertisement(Integer page,Integer status);
}
