package com.shopping.controller;
import com.shopping.mapper.UsersMapper;
import com.shopping.model.Users;
import com.shopping.model.UsersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页控制器
 */

@Controller
public class IndexController {
	@Autowired
	private UsersMapper usersMapper;
	@RequestMapping("/")
	public String index() {
		return "user/index";
	}

}
