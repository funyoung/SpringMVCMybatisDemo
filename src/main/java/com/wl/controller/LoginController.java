package com.wl.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wl.model.Account;
import com.wl.service.RegService;
import com.wl.web.util.ResponseUtils;

/**
 * 登陆控制器
 * @author wangliang
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private RegService regService;
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody String login(@RequestParam(value="username", required=true) String username, 
			@RequestParam(value="password", required=true) String password) {
		// 后端数据库校验
		Account acc = regService.getAccountByAccPwd(username, password);
		System.out.println(JSON.toJSON(acc));
		
		if(acc != null) {
			//TODO token 
			JSONObject data = new JSONObject();
			data.put("uid", acc.getId());
			data.put("token", "1111111");
			return ResponseUtils.generSuccJsonStr(data);
		} else {
			return ResponseUtils.generFailedJsonStr(100, "用户名密码错误");
		}
	}
	
//	@RequestMapping(value="login", method=RequestMethod.POST)
//	ModelAndView login(@RequestParam(value="username", required=true) String username, 
//			@RequestParam(value="password", required=true) String password) {
//		// 后端数据库校验
//		Account acc = regService.getAccountByAccPwd(username, password);
//		System.out.println(JSON.toJSON(acc));
//		
//		if(acc != null) {
//			//TODO token 
//			JSONObject data = new JSONObject();
//			data.put("uid", acc.getId());
//			data.put("token", "1111111");
//			HashMap<String, String> map = new HashMap<>();
//			map.put("uid", String.valueOf(acc.getId()));
//			return new ModelAndView("main", map);
//			//return ResponseUtils.generSuccJsonStr(data);
//		} else {
//			HashMap<String, String> map = new HashMap<>();
//			return new ModelAndView("error", map);
//			//return ResponseUtils.generFailedJsonStr(100, "用户名密码错误");
//		}
//	}
}
