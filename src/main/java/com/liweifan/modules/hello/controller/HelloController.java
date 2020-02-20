package com.liweifan.modules.hello.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liweifan.common.utils.HSSFCopySheetUtil;
import com.liweifan.modules.ImportZbms.dao.PzBdzbMapper;


@Controller
@RequestMapping(value="/hello")
public class HelloController {
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	@Autowired
	private PzBdzbMapper pzBdzbMapper;
	private HSSFSheet sheet;
	
	/*@RequestMapping(value="/sayHello")
	@ResponseBody
	public String sayHello(){
		try {
			InputStream is = new FileInputStream("D:\\works\\数据修复工作\\02卖出修复\\成品数据1.1\\07卖出清单\\北京浩海07.xlsx");
			XSSFWorkbook oldbook = new HSSFWorkbook(is);
			
			InputStream is1 = new FileInputStream("D:\\works\\数据修复工作\\02卖出修复\\成品数据1.2\\7月\\01.xlsx");
			
			HSSFWorkbook xssfWorkbook1 = new HSSFWorkbook(is1);
			
			HSSFSheet sheet2 = oldbook.getSheet("01");
			
			HSSFSheet createSheet = xssfWorkbook1.createSheet();
			
			HSSFCopySheetUtil.copySheets(createSheet,sheet2);
			
			xssfWorkbook1.write();
			
			xssfWorkbook1.close();
			is1.close();
			oldbook.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "hello word";
	}*/
	
	@Cacheable(value="HelloController.redisHello")
	@RequestMapping(value="/redisHello")
	public String redisHello(){
		redisTemplate.opsForValue().set("aa", "abc");
		System.out.println("111");
		Object object = redisTemplate.opsForValue().get("aa");
		return object.toString();
	}
	
	@RequestMapping(value="/pageHello")
	public String pageHello(){
		return "error/404";
	}
	
}
