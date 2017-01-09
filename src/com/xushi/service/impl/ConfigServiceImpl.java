package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.dao.Pay_configDao;
import com.xushi.entity.Pay_config;
import com.xushi.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService{

	@Autowired Pay_configDao pay_configDao;

	@Override
	@Transactional
	public void initPay_config() {
		List<Pay_config> configs = pay_configDao.findAll();
		if( null != configs && configs.size() > 0 ) return;
		Pay_config config = new Pay_config();
		config.setType(2);
		config.setAmount(60.0f);
		pay_configDao.save(config);
		config = new Pay_config();
		config.setType(3);
		config.setAmount(90.0f);
		pay_configDao.save(config);
		config = new Pay_config();
		config.setType(4);
		config.setAmount(180.0f);
		pay_configDao.save(config);
		config = new Pay_config();
		config.setType(5);
		config.setAmount(360.0f);
		pay_configDao.save(config);
		config = new Pay_config();
		config.setType(6);
		config.setAmount(30.0f);
		pay_configDao.save(config);
	}

	@Override
	public List<Pay_config> findPay_config() {
		return pay_configDao.findAll();
	}

}
