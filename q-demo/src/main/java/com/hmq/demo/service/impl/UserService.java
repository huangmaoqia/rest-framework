package com.hmq.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmq.demo.dao.IUserDao;
import com.hmq.demo.model.po.User;
import com.hmq.demo.service.IUserService;
import com.hmq.framework.service.impl.GenService;

@Service
@Transactional
public class UserService extends GenService< User, String, IUserDao> implements IUserService {

}
