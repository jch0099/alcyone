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
 * 图片
 * @author penken
 * 
 */
@Entity
@Table(name = "video_source")
public class Video_source implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name="myGenerator", table="myidlist", pkColumnName = "keyname", pkColumnValue="video_source", valueColumnName = "keyid", allocationSize=1 )
	@GeneratedValue(strategy = GenerationType.TABLE,generator="myGenerator") 
	//主键
	@Column(name = "id")
	private Integer id; 
	
	//标题
	@Column(name = "title", length = 200)
	private String title;
	
	//视频图片
	@Column(name = "name", length = 200)
	private String name;
	
	//状态  1 正常  2停用
	@Column(name= "status")
	private Integer status = 1;
	
	//创建時間
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
