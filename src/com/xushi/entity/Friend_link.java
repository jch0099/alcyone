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
 * 友情链接
 * @author penken
 * 
 */
@Entity
@Table(name = "friend_link")
public class Friend_link implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name="myGenerator", table="myidlist", pkColumnName = "keyname", pkColumnValue="friend_link", valueColumnName = "keyid", allocationSize=1 )
	@GeneratedValue(strategy = GenerationType.TABLE,generator="myGenerator")
	//主键
	@Column(name = "id")
	private Integer id;
	
	//显示名称
	@Column(name = "name", length = 200)
	private String name;
	
	//广告url
	@Column(name = "url", length = 2000)
	private String url;
	
	//说明
	@Column(name = "summary", length = 65535)
	private String summary;
	
	//状态 1启用 2未启用
	@Column(name = "status")
	private Integer status;
	
	//创建日期
	@Column(name = "create_time", length = 20)
	private String create_time;
	
	//修改時間
	@Column(name = "update_time", length = 20)
	private String update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}
