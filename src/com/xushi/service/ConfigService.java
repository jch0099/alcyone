package com.xushi.service;

import java.util.List;

import com.xushi.entity.Pay_config;


public interface ConfigService {
	void initPay_config();
	List<Pay_config> findPay_config();
}
