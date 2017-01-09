package com.xushi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 订单
 * @author penken
 * 
 */
@Entity
@Table(name = "order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name="myGenerator", table="myidlist", pkColumnName = "keyname", pkColumnValue="order", valueColumnName = "keyid", allocationSize=1 )
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
	
	//类型  1 购买单个视频 2购买用户vip
	@Column(name = "type")
	private Integer type;
	
	//用户购买视频id,type为1时
	@Column(name = "video_id")
	private Integer video_id;
	
	//用户vip时长,type为2时     月为单位
	@Column(name = "time_type")
	private Integer time_type = 1;
	
	//价钱
	@Column(name = "amount")
	private Float amount;
	
	//状态 1.已支付  2.未支付
	@Column(name = "status")
	private Integer status = 2;
	
	//创建日期
	@Column(name = "create_time", length = 20)
	private String create_time;

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

	public Integer getTime_type() {
		return time_type;
	}

	public void setTime_type(Integer time_type) {
		this.time_type = time_type;
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

}
