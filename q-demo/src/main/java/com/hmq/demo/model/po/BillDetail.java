package com.hmq.demo.model.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hmq.framework.model.GenPO;

@Entity
@Table(name = "t_bill_detail")
public class BillDetail extends GenPO<String> {

	private String billId;

	private String goodsId;

	private long amount;

	public String getBillId() {
		return billId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public long getAmount() {
		return amount;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}