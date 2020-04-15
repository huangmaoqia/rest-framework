package com.hmq.demo.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmq.demo.model.po.User;
import com.hmq.demo.service.IUserService;
import com.hmq.framework.controller.impl.GenController;

@RestController
@RequestMapping("/user")
public class UserController extends GenController<User, String, IUserService> {

}
