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
	
	//类型  2两个月,3一季,4半年,5一年,6任意(每月的价钱)
	@Column(name = "type")
	private Integer type;
	
	//类型  2两个月,3一季,4半年,5一年,6任意(每月的价钱)
	@Column(name = "name",length=10)
	private String name;
	
	//价格
	@Column(name = "amount")
	private Float amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
}
