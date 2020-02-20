package com.liweifan.modules.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liweifan.modules.sys.util.UserUtils;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(Model model){
		if(UserUtils.getUser()!=null){
			return "redirect:/";
		}
		return "/main/login";
	}
}
