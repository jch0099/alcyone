package com.xushi.entity.vo;

public class AlipayVo {
	private String tradeNo;
	private String Money;
	private String title;
	private String memo;
	private String alipay_account;
	private String tenpay_account;
	private String Gateway;
	private String Sign;
	private String Paytime;
	
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getMoney() {
		return Money;
	}
	public void setMoney(String money) {
		Money = money;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAlipay_account() {
		return alipay_account;
	}
	public void setAlipay_account(String alipay_account) {
		this.alipay_account = alipay_account;
	}
	public String getTenpay_account() {
		return tenpay_account;
	}
	public void setTenpay_account(String tenpay_account) {
		this.tenpay_account = tenpay_account;
	}
	public String getGateway() {
		return Gateway;
	}
	public void setGateway(String gateway) {
		Gateway = gateway;
	}
	public String getSign() {
		return Sign;
	}
	public void setSign(String sign) {
		Sign = sign;
	}
	public String getPaytime() {
		return Paytime;
	}
	public void setPaytime(String paytime) {
		Paytime = paytime;
	}
}
