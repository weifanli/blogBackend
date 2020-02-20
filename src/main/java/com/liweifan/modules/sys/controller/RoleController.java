package com.liweifan.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liweifan.common.base.controller.imp.AbstractBaseController;
import com.liweifan.modules.sys.entity.SysRole;
import com.liweifan.modules.sys.service.RoleService;
@Controller
@RequestMapping(value="/role")
public class RoleController extends AbstractBaseController<SysRole, RoleService>{

}
