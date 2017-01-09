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
 * 视频
 * @author penken
 * 
 */
@Entity
@Table(name = "video")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name="myGenerator", table="myidlist", pkColumnName = "keyname", pkColumnValue="video", valueColumnName = "keyid", allocationSize=1 )
	@GeneratedValue(strategy = GenerationType.TABLE,generator="myGenerator") 
	//主键
	@Column(name = "id")
	private Integer id; 
	
	//标题
	@Column(name = "title", length = 20)
	private String title;
	
	//视频url
	@Column(name = "url", length = 255)
	private String url;
	
	//说明
	@Column(name = "summary", length = 65535)
	private String summary;
	
	//视频类型 1表示免费  2表示收费
	@Column(name = "is_free")
	private Integer is_free = 1;
	
	//收费时视频价格的
	@Column(name = "amount")
	private Float amount;
	
	//状态  1 正常  2停用
	@Column(name= "status")
	private Integer status = 1;
	
	//观看数  
	@Column(name= "read_num")
	private Integer read_num = 0;
	
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

	public Integer getIs_free() {
		return is_free;
	}

	public void setIs_free(Integer is_free) {
		this.is_free = is_free;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRead_num() {
		return read_num;
	}

	public void setRead_num(Integer read_num) {
		this.read_num = read_num;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

}
