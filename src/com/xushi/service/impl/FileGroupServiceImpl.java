package com.xushi.service.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.page.PageRequest;
import com.xushi.core.page.Sort;
import com.xushi.dao.FileGroupDao;
import com.xushi.entity.File_group;
import com.xushi.entity.User;
import com.xushi.service.FileGroupService;
import com.xushi.util.NumberUtil;
import com.xushi.util.date.DateTimeUtil;

@Service
public class FileGroupServiceImpl implements FileGroupService{

	@Autowired FileGroupDao fileGroupDao;

	@Override
	public List<File_group> findFileGroup(User user) {
		return fileGroupDao.findBy("user", user);
	}

	@Override
	@Transactional
	public void deleteFileGroup(File_group file_group) {
		fileGroupDao.delete(file_group);
	}

	@Override
	@Transactional
	public void saveFileGroup(File_group file_group) {
		file_group.setLast_time(DateTimeUtil.getCurDateTime());
		if( NumberUtil.toInt(file_group.getId()) > 0 ) {
			fileGroupDao.update(file_group);
		}else {
			file_group.setCreate_time(DateTimeUtil.getCurDateTime());
			String uuid = UUID.randomUUID().toString();
			file_group.setUuid(uuid);
			fileGroupDao.save(file_group);
		}
		fileGroupDao.update(file_group);
	}

	@Override
	public File_group getFileGroup(Integer id) {
		return fileGroupDao.getById(id);
	}

	@Override
	public File_group getFileGroupByUUid(String uuid) {
		return fileGroupDao.getBy("uuid", uuid);
	}

	@Override
	public List<File_group> findFileGroup(User sourceUser, Integer count) {
		PageRequest pr = new PageRequest(0, count);
		Sort sort = new Sort(false, "id");
		pr.setSort(sort);
		return fileGroupDao.findBy("user", sourceUser, pr).getResults();
	}
	
	
}
