package com.xushi.service;

import java.util.List;

import com.xushi.entity.File_group;
import com.xushi.entity.User;

public interface FileGroupService {
	
	List<File_group> findFileGroup(User user);
	
	void deleteFileGroup(File_group file_group);
	
	void saveFileGroup(File_group file_group);
	
	File_group getFileGroup(Integer id);
	
	File_group getFileGroupByUUid(String uuid);

	List<File_group> findFileGroup(User sourceUser, Integer count);
}
