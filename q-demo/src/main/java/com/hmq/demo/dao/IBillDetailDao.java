package com.hmq.demo.dao;

import org.springframework.stereotype.Repository;

import com.hmq.demo.model.po.BillDetail;
import com.hmq.framework.dao.IGenDao;

@Repository
public interface IBillDetailDao extends IGenDao<BillDetail, String> {

}