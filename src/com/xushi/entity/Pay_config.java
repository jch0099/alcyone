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
 * 支付配置
 * @author penken
 *
 */
@Entity
@Table(name = "pay_config")
public class Pay_config  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name="myGenerator", table="myidlist", pkColumnName = "keyname", pkColumnValue="pay_config", valueColumnName = "keyid", allocationSize=1 )
	@GeneratedValue(strategy = GenerationType.TABLE,generator="myGenerator") 
	//主键
	@Column(name = "id")
	private Integer id;
	
	//时长
	@Column(name = "month_length")
	private Integer month_length;
	
	//显示名字  
	@Column(name = "name",length=10)
	private String name;
	
	//价格 (type为2时为 每个月的价钱)
	@Column(name = "amount")
	private Float amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMonth_length() {
		return month_length;
	}

	public void setMonth_length(Integer month_length) {
		this.month_length = month_length;
	}
}
