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
import javax.persistence.Transient;

/**
 * 文件表
 * @author penken
 * 
 */
@Entity
@Table(name = "file_group")
public class File_group implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name="myGenerator", table="myidlist", pkColumnName = "keyname", pkColumnValue="file_group", valueColumnName = "keyid", allocationSize=1 )
	@GeneratedValue(strategy = GenerationType.TABLE,generator="myGenerator") 
	//主键
	@Column(name = "id")
	private Integer id; 
	
	//用户
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user; 
	
	//图片名称
	@Column(name = "img_name", length = 50)
	private String img_name;
	
	//音频名称
	@Column(name = "voice_name", length = 50)
	private String voice_name;
	
	//唯一id
	@Column(name = "uuid", length = 50)
	private String uuid;
	
	//创建時間
	@Column(name = "create_time", length = 20)
	private String create_time;
	
	//修改時間
	@Column(name = "last_time", length = 20)
	private String last_time;
	
	//状态  1 草稿  2 发布
	@Column(name= "status")
	private Integer status = 1;

	@Transient
	private String qrcode;
	
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

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public String getVoice_name() {
		return voice_name;
	}

	public void setVoice_name(String voice_name) {
		this.voice_name = voice_name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getLast_time() {
		return last_time;
	}

	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
