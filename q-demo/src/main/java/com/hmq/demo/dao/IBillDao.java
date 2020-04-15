package com.hmq.demo.dao;

import org.springframework.stereotype.Repository;

import com.hmq.demo.model.po.Bill;
import com.hmq.framework.dao.IGenDao;

@Repository
public interface IBillDao extends IGenDao<Bill, String> {

}