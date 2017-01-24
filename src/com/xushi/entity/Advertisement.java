package com.xushi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 广告
 * @author penken
 * 
 */
@Entity
@Table(name = "advertisement")
public class Advertisement implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	//主键
	@Column(name = "id")
	private Integer id;
	
	//标题
	@Column(name = "title", length = 200)
	private String title;
	
	//广告图片
	@Column(name = "img", length = 200)
	private String img;
	
	//广告url
	@Column(name = "url", length = 2000)
	private String url;
	
	//说明
	@Column(name = "summary", length = 65535)
	private String summary;
	
	//广告位置 1首页 2播放页
	@Column(name = "page")
	private Integer page;
	
	//广告位置 1-10 各个位置
	@Column(name = "position")
	private Integer position;
	
	//状态 1启用 2未启用
	@Column(name = "status")
	private Integer status;
	
	//修改時間
	@Column(name = "update_time", length = 20)
	private String update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
