package com.design.module.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.design.module.entity.User;
import com.design.module.service.UserService;
import com.design.framework.base.controller.BaseController;

/**
 * 用户
 * @author John
 * @datatime 2018-09-11 16:54:18
 */
@Controller
@RequestMapping("/pc/user")
public class UserController extends BaseController<User,String>{
	
	@Resource
	private UserService userService;

	@PostConstruct
	@Override
	public void initialize() {
		super.setBaseService(userService);
		
	}
	
}

