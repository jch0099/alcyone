package com.xushi.service;

import java.util.List;

import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Friend_link;


public interface Friend_linkService {
	Page<Friend_link> findFriend_linkPage(PageRequest pr);
	Friend_link getFriend_link(Integer id);
	void saveFriend_link(Friend_link friend_link);
	void deleteFriend_link(Friend_link friend_link);
	List<Friend_link> findFriend_link(Integer status);
}
