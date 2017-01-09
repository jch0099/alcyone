package com.xushi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 用户vip
 * @author penken
 * 
 */
@Entity
@Table(name = "user_vip")
public class User_vip implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name="myGenerator", table="myidlist", pkColumnName = "keyname", pkColumnValue="user_vip", valueColumnName = "keyid", allocationSize=1 )
	@GeneratedValue(strategy = GenerationType.TABLE,generator="myGenerator") 
	//主键
	@Column(name = "id")
	private Integer id;
	
	//用户
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	//终止日期
	@Column(name = "end_date", length = 20)
	private String end_date;
	
	//剩余视频数
	@Column(name = "count")
	private Integer count;
	
	//创建日期
	@Column(name = "create_time", length = 20)
	private String create_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
}
