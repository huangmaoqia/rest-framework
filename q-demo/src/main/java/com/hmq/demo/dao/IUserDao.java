package com.hmq.demo.dao;

import org.springframework.stereotype.Repository;

import com.hmq.demo.model.po.User;
import com.hmq.framework.dao.IGenDao;

@Repository
public interface IUserDao extends IGenDao<User, String> {

}