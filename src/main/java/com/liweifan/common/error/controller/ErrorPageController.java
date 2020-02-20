package com.liweifan.common.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/error")
public class ErrorPageController {
	@RequestMapping(value="/page404")
	public String page404(){
		return "error/404";
	}
}
