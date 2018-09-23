package com.design.module.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.framework.base.service.impl.BaseServiceImpl;
import com.design.module.dao.UserDao;
import com.design.module.entity.User;
import com.design.module.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements UserService {
	@Resource
	private UserDao userDao;
	
	@PostConstruct
	public void initialize(){
		super.setBaseDao(userDao);
	}

	
}

