package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.DaoException;
import com.xushi.dao.Pay_configDao;
import com.xushi.entity.Pay_config;
import com.xushi.service.ConfigService;
import com.xushi.util.NumberUtil;

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
		config.setName("两个月");
		config.setAmount(60.0f);
		pay_configDao.save(config);
		config = new Pay_config();
		config.setType(3);
		config.setName("一季");
		config.setAmount(90.0f);
		pay_configDao.save(config);
		config = new Pay_config();
		config.setType(4);
		config.setName("半年");
		config.setAmount(180.0f);
		pay_configDao.save(config);
		config = new Pay_config();
		config.setType(5);
		config.setAmount(360.0f);
		config.setName("一年");
		pay_configDao.save(config);
		config = new Pay_config();
		config.setType(6);
		config.setName("任意");
		config.setAmount(30.0f);
		pay_configDao.save(config);
	}

	@Override
	public List<Pay_config> findPay_config() {
		return pay_configDao.findAll();
	}

	@Override
	public Pay_config getPay_config(Integer id) {
		return pay_configDao.getById(id);
	}

	@Override
	@Transactional
	public void savePay_config(Pay_config pay_config) {
		Pay_config temp = pay_configDao.getBy("type", pay_config.getType());
		if( null != temp && NumberUtil.toInt(temp.getId()) != NumberUtil.toInt(pay_config.getId()) ) throw new DaoException("类型不能重复!");
		if( pay_config.getId() == null ) {
			pay_configDao.save(pay_config);
		}else {
			pay_configDao.update(pay_config);
		}
	}

}
