package com.xushi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 * 订单
 * @author penken
 * 
 */
@Entity
@Table(name = "pay_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name="myGenerator", table="myidlist", pkColumnName = "keyname", pkColumnValue="pay_order", valueColumnName = "keyid", allocationSize=1 )
	@GeneratedValue(strategy = GenerationType.TABLE,generator="myGenerator") 
	//主键
	@Column(name = "id")
	private Integer id;
	
	//用户id
	@Column(name = "user_id")
	private Integer user_id;
	
	//订单号唯一
	@Column(name = "order_num", length = 255)
	private String order_num;
	
	//阿里订单号唯一
	@Column(name = "alipay_order_num", length = 255)
	private String alipay_order_num;
	
	//支付帐号
	@Column(name = "pay_account", length = 255)
	private String pay_account;
	
	//支付时间
	@Column(name = "pay_time", length = 255)
	private String pay_time;
	
	//类型  1 购买单个视频 2购买用户vip 3打赏
	@Column(name = "type")
	private Integer type;
	
	//用户购买视频id,type为1/3时
	@Column(name = "video_id")
	private Integer video_id;
	
	//用户vip时长,type为2时     月为单位
	@Column(name = "month_length")
	private Integer month_length = 1;
	
	//价钱
	@Column(name = "amount")
	private Float amount;
	
	//状态 1.已支付  2.未支付 3.补足订单
	@Column(name = "status")
	private Integer status = 2;
	
	//创建日期
	@Column(name = "create_time", length = 20)
	private String create_time;
	
	//创建日期
	@Column(name = "update_time", length = 20)
	private String update_time;
	
	@Transient
	private String user_account;
	@Transient
	private String video_title;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVideo_id() {
		return video_id;
	}

	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getAlipay_order_num() {
		return alipay_order_num;
	}

	public void setAlipay_order_num(String alipay_order_num) {
		this.alipay_order_num = alipay_order_num;
	}

	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public String getPay_account() {
		return pay_account;
	}

	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}

	public Integer getMonth_length() {
		return month_length;
	}

	public void setMonth_length(Integer month_length) {
		this.month_length = month_length;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getVideo_title() {
		return video_title;
	}

	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}
}
