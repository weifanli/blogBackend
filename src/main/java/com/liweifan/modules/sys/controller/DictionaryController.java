package com.liweifan.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liweifan.common.base.controller.imp.AbstractBaseController;
import com.liweifan.modules.sys.entity.SysDictionary;
import com.liweifan.modules.sys.service.DictionaryService;
@Controller
@RequestMapping(value="/dictionary")
public class DictionaryController extends AbstractBaseController<SysDictionary, DictionaryService>{
	
}
