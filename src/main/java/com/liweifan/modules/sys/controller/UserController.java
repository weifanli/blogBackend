package com.liweifan.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liweifan.common.base.controller.imp.AbstractBaseController;
import com.liweifan.modules.sys.entity.SysUser;
import com.liweifan.modules.sys.service.UserService;
@Controller
@RequestMapping(value="/user")
public class UserController extends AbstractBaseController<SysUser, UserService>{

}
