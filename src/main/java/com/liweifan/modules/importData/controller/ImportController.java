package com.liweifan.modules.importData.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.liweifan.common.utils.DateUtils;
import com.liweifan.common.utils.HSSFCopySheetUtil;
import com.liweifan.common.utils.StringUtils;
import com.liweifan.modules.importData.dao.BeginningAmountMapper;
import com.liweifan.modules.importData.dao.ChukuMapper;
import com.liweifan.modules.importData.dao.InvoicingMapper;
import com.liweifan.modules.importData.dao.RukuMapper;
import com.liweifan.modules.importData.entity.BeginningAmount;
import com.liweifan.modules.importData.entity.ChukuOriginal;
import com.liweifan.modules.importData.entity.Invoicing;
import com.liweifan.modules.importData.entity.RukuOriginal;

@Controller
@RequestMapping(value="/import")
public class ImportController {
	@Autowired
	private RukuMapper rukuMapper;
	@Autowired
	private ChukuMapper chukuMapper;
	
	@Autowired
	private InvoicingMapper invoicingMapper;
	
	@Autowired
	private BeginningAmountMapper beginningAmountMapper;
	
	private static Map<String,String> timeMap = new HashMap<String, String>();
	static {
		timeMap.put("1号货品明细","01");timeMap.put("2号货品明细","02");timeMap.put("3号货品明细","03");
		timeMap.put("4号货品明细","04");timeMap.put("5号货品明细","05");timeMap.put("6号货品明细","06");
		timeMap.put("7号货品明细","07");timeMap.put("8号货品明细","08");timeMap.put("9号货品明细","09");
		timeMap.put("10号货品明细","10");timeMap.put("11号货品明细","11");timeMap.put("12号货品明细","12");
		timeMap.put("13号货品明细","13");timeMap.put("14号货品明细","14");timeMap.put("15号货品明细","15");
		timeMap.put("16号货品明细","16");timeMap.put("17号货品明细","17");timeMap.put("18号货品明细","18");
		timeMap.put("19号货品明细","19");timeMap.put("20号货品明细","20");timeMap.put("21号货品明细","21");
		timeMap.put("22号货品明细","22");timeMap.put("23号货品明细","23");timeMap.put("24号货品明细","24");
		timeMap.put("25号货品明细","25");timeMap.put("26号货品明细","26");timeMap.put("27号货品明细","27");
		timeMap.put("28号货品明细","28");timeMap.put("29号货品明细","29");timeMap.put("30号货品明细","30");
		timeMap.put("31号货品明细","31");
		timeMap.put("1","01");timeMap.put("2","02");timeMap.put("3","03");
		timeMap.put("4","04");timeMap.put("5","05");timeMap.put("6","06");
		timeMap.put("7","07");timeMap.put("8","08");timeMap.put("9","09");
		timeMap.put("10","10");timeMap.put("11","11");timeMap.put("12","12");
		timeMap.put("13","13");timeMap.put("14","14");timeMap.put("15","15");
		timeMap.put("16","16");timeMap.put("17","17");timeMap.put("18","18");
		timeMap.put("19","19");timeMap.put("20","20");timeMap.put("21","21");
		timeMap.put("22","22");timeMap.put("23","23");timeMap.put("24","24");
		timeMap.put("25","25");timeMap.put("26","26");timeMap.put("27","27");
		timeMap.put("28","28");timeMap.put("29","29");timeMap.put("30","30");
		timeMap.put("31","31");
	}
	
	@RequestMapping(value="/importRuku")
	@ResponseBody
	public String importRuku(){
		String[] listdir=new File("D:\\works\\20191121数据清洗\\导入文件\\01入库").list();
		for(int i=0;i<listdir.length;i++){
			String dirName = listdir[i];
			String[] listFile = new File("D:\\works\\20191121数据清洗\\导入文件\\01入库\\"+dirName).list();
			for(int j=0;j<listFile.length;j++){
				String filePath = "D:\\works\\20191121数据清洗\\导入文件\\01入库\\"+dirName+"\\"+listFile[j];
				
				if(listFile[j].indexOf("$")>0){
					System.out.println(filePath);
				}
				try {
					XSSFWorkbook srcwb = new XSSFWorkbook(new FileInputStream(filePath));
					
					doRukuExcel(srcwb,dirName,listFile[j].substring(0, listFile[j].length()-7));
					
					srcwb.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	private void doRukuExcel(XSSFWorkbook srcwb,String dirName,String cj) {
		XSSFSheet sheet = srcwb.getSheetAt(0);
		//获取时间
		XSSFCell sjCell = sheet.getRow(2).getCell(8);
		
		String sjStr = sjCell.toString();
		
		//if(sjStr.equals("2010.01.31")){
			System.out.println(dirName+"--"+cj+"--"+sjStr);
		//}
		Date qcsj = DateUtils.parseStrToDate(sjStr, DateUtils.DATE_FORMAT_POINTYYYYMMDD);
		qcsj.setHours(0);qcsj.setMinutes(0);qcsj.setMinutes(0);
		
		Date qmsj = DateUtils.parseStrToDate(sjStr, DateUtils.DATE_FORMAT_POINTYYYYMMDD);
		qmsj.setHours(23);qmsj.setMinutes(0);qmsj.setMinutes(0);
		
		int lastRowNum = sheet.getLastRowNum();
		for(int i=9;i<=lastRowNum;i++){
			XSSFRow row = sheet.getRow(i);
			String hj = row.getCell(1).toString();
			if(null != row){
				if(null != row.getCell(1) && (hj.equals("总计") || hj.equals("合计"))){
					break;
				}
				RukuOriginal ruku = new RukuOriginal();
				
				if(null!=row.getCell(2) && row.getCell(2).toString()==""){
					System.out.println(dirName+"--"+cj);
				}
				ruku.setSpdm(null==row.getCell(2)?"":row.getCell(2).toString());
				if(null!=row.getCell(3) && row.getCell(3).toString().equals("Yuki原创精装本鲸鱼款	") && qcsj.equals(DateUtils.parseStrToDate("2018-01-12 00:00:00", DateUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS))){
					System.out.println(dirName+"--"+cj);
				}
				
				ruku.setSpmc(null==row.getCell(3)?"":row.getCell(3).toString());
				ruku.setSpgg(null==row.getCell(5)?"":row.getCell(5).toString());
				ruku.setDw(null==row.getCell(6)?"":row.getCell(6).toString());
				ruku.setDj(null==row.getCell(7)?0.0:row.getCell(7).getNumericCellValue());
				ruku.setSl(null==row.getCell(8)?0:new Double(row.getCell(8).getNumericCellValue()).intValue());
				ruku.setJe(null==row.getCell(9)?0.0:row.getCell(9).getNumericCellValue());
				ruku.setQcsj(qcsj);
				ruku.setQmsj(qmsj);
				ruku.setWjlj(dirName+cj);
				rukuMapper.insert(ruku);
			}
		}
	}
	
	@RequestMapping(value="/importCuku")
	@ResponseBody
	public String importCuku(){
		String[] listFile = new File("D:\\works\\20191121数据清洗\\导入文件\\常规出库").list();
		for(int j=0;j<listFile.length;j++){
			String filePath = "D:\\works\\20191121数据清洗\\导入文件\\常规出库\\"+listFile[j];
			
			if(listFile[j].indexOf("$")>0){
				System.out.println(filePath);
			}
			try {
				XSSFWorkbook srcwb = new XSSFWorkbook(new FileInputStream(filePath));
				
				doCukuExcel(srcwb,listFile[j].substring(0, listFile[j].length()-5));
				
				srcwb.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	private void doCukuExcel(XSSFWorkbook srcwb, String timeStr) {
		int numberOfSheets = srcwb.getNumberOfSheets();
		for(int j=0;j<numberOfSheets;j++){
			String sheetName = srcwb.getSheetName(j);
			if(null!=timeMap.get(sheetName)){
				String time = timeStr+timeMap.get(sheetName);
				System.out.println(time);
				
				Date qcsj = DateUtils.parseStrToDate(time, DateUtils.DATE_FORMAT_YYYYMMDD);
				qcsj.setHours(0);qcsj.setMinutes(0);qcsj.setMinutes(0);
				
				Date qmsj = DateUtils.parseStrToDate(time, DateUtils.DATE_FORMAT_YYYYMMDD);
				qmsj.setHours(23);qmsj.setMinutes(0);qmsj.setMinutes(0);
				
				XSSFSheet sheet = srcwb.getSheet(sheetName);
				
				int lastRowNum = sheet.getLastRowNum();
				
				XSSFRow hread = sheet.getRow(0);
				short lastCellNum = hread.getLastCellNum();
				
				int spmcNum = 0;
				int spslNum = 0;
				int spdjNum = 0;
				int spjeNum = 0;
				for(short i=0;i<lastCellNum;i++){
					XSSFCell text = hread.getCell(i);
					if(null != text){
						if(text.toString().equals("产品名称") || text.toString().equals("商品名称")){
							spmcNum=i;
						}else if(text.toString().equals("数量")){
							spslNum=i;
						}else if(text.toString().equals("单价")){
							spdjNum=i;
						}else if(text.toString().equals("金额")){
							spjeNum=i;
						}
					}
				}
				if(spmcNum==0 || spslNum==0 || spjeNum==0){
					System.err.println(time+"商品名称|数量|金额未找到");
				}
				
				for(int i=1;i<=lastRowNum;i++){
					XSSFRow row = sheet.getRow(i);
					//String hj = row.getCell(1).toString();
					if(null != row){
						if(null == row.getCell(1)|| StringUtil.isEmpty(row.getCell(1).toString().trim())){
							break;
						}
						ChukuOriginal chuku = new ChukuOriginal();
						
						if(StringUtils.isEmpty(row.getCell(spmcNum))){
							continue;
						}
						chuku.setSpmc(null==row.getCell(spmcNum)?"":row.getCell(spmcNum).toString());
						
						if(null != row.getCell(spmcNum) && row.getCell(spmcNum).equals("100.0")){
							System.out.println("");
						}
						
						chuku.setSl(null==row.getCell(spslNum)?0:new Double(row.getCell(spslNum).getNumericCellValue()).intValue());
						chuku.setDj(null==row.getCell(spdjNum)?0.0:row.getCell(spdjNum).getNumericCellValue());
						chuku.setJe(null==row.getCell(spjeNum)?0.0:row.getCell(spjeNum).getNumericCellValue());
						chuku.setQcsj(qcsj);
						chuku.setQmsj(qmsj);
						chukuMapper.insert(chuku);
					}
				}
			}
		}
	}
	@RequestMapping(value="/insertExcel")
	@ResponseBody
	public void insertExcel(){
		inserte(0,50000,"进销存1.xlsx");
		inserte(50000,50000,"进销存2.xlsx");
		inserte(100000,50000,"进销存3.xlsx");
		inserte(150000,50000,"进销存4.xlsx");
		inserte(200000,50000,"进销存5.xlsx");
		inserte(250000,68318,"进销存6.xlsx");
	}
	
	public void inserte(Integer start,Integer end,String name){
		List<BeginningAmount> cklist = beginningAmountMapper.selectAll();
		
		Map<String,BeginningAmount> ckMap = getCkMap(cklist);
		
		//获取所有进出数据
		List<Invoicing> invoicingList = invoicingMapper.selectL(start,end);
		
		String filePath = "D:\\works\\20191121数据清洗\\导出文件\\"+name;
		try {
			XSSFWorkbook srcwb = new XSSFWorkbook(new FileInputStream(filePath));
			
			XSSFSheet sheetAt = srcwb.getSheetAt(0);
			//setHread(sheetAt);
			for(int i=0;i<invoicingList.size();i++){
				System.out.println(i);
				Invoicing invoicing = invoicingList.get(i);
				
				XSSFRow createRow = sheetAt.createRow(i+1);
				createRow.createCell(0).setCellValue(invoicing.getQcsj());//期初时间
				createRow.createCell(1).setCellValue(invoicing.getQmsj());//期末时间
				
				createRow.createCell(7).setCellValue(invoicing.getSpmc());//商品名称
				
				BeginningAmount beginningAmount = ckMap.get(invoicing.getSpmc().trim());
				
				createRow.createCell(11).setCellValue(beginningAmount.getProductCount());//期初库存
				createRow.createCell(12).setCellValue(beginningAmount.getProductJe());//期初金额
				Integer cgrksl = (null == invoicing.getCgrksl()?0:invoicing.getCgrksl());//采购入库数量
				createRow.createCell(13).setCellValue(cgrksl);
				Double cgrkje = (null == invoicing.getCgrkje()?0:invoicing.getCgrkje());//采购入库金额
				createRow.createCell(14).setCellValue(cgrkje);
				Double xsje = (null == invoicing.getXsje()?0:invoicing.getXsje());//销售金额
				createRow.createCell(17).setCellValue(xsje);
				Integer xssl = (null == invoicing.getXssl()?0:invoicing.getXssl());//销售数量
				createRow.createCell(19).setCellValue(xssl);
				
				createRow.createCell(26).setCellValue(null == invoicing.getB2bcksl()?0:invoicing.getB2bcksl());//B2B出库数量
				
				beginningAmount.setProductCount(beginningAmount.getProductCount()+cgrksl-xssl);
				beginningAmount.setProductJe(beginningAmount.getProductJe()+cgrkje-xssl*beginningAmount.getProductDj());
				createRow.createCell(27).setCellValue(beginningAmount.getProductCount());//期末库存
				createRow.createCell(28).setCellValue(beginningAmount.getProductJe());//期末金额
				
				ckMap.put(invoicing.getSpmc(), beginningAmount);
			}
			
			FileOutputStream fileOut = new FileOutputStream(filePath); 
			srcwb.write(fileOut); 
	        fileOut.flush(); 
	        fileOut.close();
			srcwb.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void setHread(XSSFSheet sheetAt) {
		XSSFRow hread = sheetAt.createRow(0);
		hread.createCell(0).setCellValue("期初时间");
		hread.createCell(1).setCellValue("期末时间");
		hread.createCell(2).setCellValue("仓库名称");
		hread.createCell(3).setCellValue("商品类别");
		hread.createCell(4).setCellValue("品牌");
		hread.createCell(5).setCellValue("商品代码");
		hread.createCell(6).setCellValue("商品条码");
		hread.createCell(7).setCellValue("商品名称");
		hread.createCell(8).setCellValue("商品简称");
		hread.createCell(9).setCellValue("规格代码");
		hread.createCell(10).setCellValue("规格名称");
		hread.createCell(11).setCellValue("期初库存");
		hread.createCell(12).setCellValue("期初金额");
		hread.createCell(13).setCellValue("采购入库数量");
		hread.createCell(14).setCellValue("采购入库金额");
		hread.createCell(15).setCellValue("采购退货金额");
		hread.createCell(16).setCellValue("采购退货数量");
		hread.createCell(17).setCellValue("销售金额");
		hread.createCell(18).setCellValue("退货金额");
		hread.createCell(19).setCellValue("销售数量");
		hread.createCell(20).setCellValue("退货数量");
		hread.createCell(21).setCellValue("调整数量");
		hread.createCell(22).setCellValue("调拨出库数量");
		hread.createCell(23).setCellValue("调拨入库数量");
		hread.createCell(24).setCellValue("其他入库数量");
		hread.createCell(25).setCellValue("其他出库数量");
		hread.createCell(26).setCellValue("B2B出库数量");
		hread.createCell(27).setCellValue("期末库存");
		hread.createCell(28).setCellValue("期末金额");
		hread.createCell(29).setCellValue("期末成本价");
		hread.createCell(30).setCellValue("停用");
	}

	private Map<String, BeginningAmount> getCkMap(List<BeginningAmount> cklist) {
		Map<String, BeginningAmount> map = new HashMap<String, BeginningAmount>();
		for(BeginningAmount BeginningAmount:cklist){
			map.put(BeginningAmount.getProductName().trim(), BeginningAmount);
		}
		return map;
	}

	@RequestMapping(value="/insertTime")
	@ResponseBody
	public String insertTime(){
		String startTime = "20180101";
		String endTime = "20190931";
		
		Date start = DateUtils.parseStrToDate(startTime, DateUtils.DATE_FORMAT_YYYYMMDD);
		Date end = DateUtils.parseStrToDate(endTime, DateUtils.DATE_FORMAT_YYYYMMDD);	
			
		while(start.before(end)||start.equals(end)){
			
			Date qcsj = DateUtils.parseStrToDate(DateUtils.parseDateToStr(start,DateUtils.DATE_FORMAT_YYYYMMDD), DateUtils.DATE_FORMAT_YYYYMMDD);
			
			qcsj.setHours(0);qcsj.setMinutes(0);qcsj.setMinutes(0);
			
			Date qmsj = DateUtils.parseStrToDate(DateUtils.parseDateToStr(start,DateUtils.DATE_FORMAT_YYYYMMDD), DateUtils.DATE_FORMAT_YYYYMMDD);
			qmsj.setHours(23);qcsj.setMinutes(0);qcsj.setMinutes(0);
			
			
			chukuMapper.insertTime(DateUtils.parseDateToStr(qcsj, DateUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS),DateUtils.parseDateToStr(qmsj, DateUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));
			
			Calendar cal = Calendar.getInstance();
	   	 	cal.setTime(start);
	   	 	cal.add(Calendar.DATE,1);
	   		start=cal.getTime();
		}
		
		
		return "";
	}
}
