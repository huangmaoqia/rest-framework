package com.hmq.demo.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.hmq.demo.model.po.Bill;
import com.hmq.demo.model.po.BillDetail;

public class BillVO extends Bill{
	List<BillDetail> billDetailList=new ArrayList<>();

	public List<BillDetail> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<BillDetail> billDetailList) {
		this.billDetailList = billDetailList;
	}
}
