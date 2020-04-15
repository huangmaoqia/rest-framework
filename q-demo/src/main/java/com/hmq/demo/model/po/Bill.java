package com.hmq.demo.model.po;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hmq.framework.model.GenPO;

@Entity
@Table(name = "t_bill")
public class Bill extends GenPO<String> {

	private BigDecimal money;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}