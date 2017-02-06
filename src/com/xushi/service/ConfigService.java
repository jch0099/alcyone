package com.xushi.service;

import java.util.List;

import com.xushi.entity.Pay_config;


public interface ConfigService {
	void initPay_config();
	List<Pay_config> findPay_config();
	Pay_config getPay_config(Integer id);
	Pay_config getPay_configByLength(Integer length);
	void savePay_config(Pay_config pay_config);
	void deleteConfig(Pay_config config);
}
