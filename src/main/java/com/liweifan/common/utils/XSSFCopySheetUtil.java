package com.liweifan.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFCopySheetUtil {
	public static void main(String[] args) throws Exception {  
		XSSFCopySheetUtil ds = new XSSFCopySheetUtil();
		/*//1.获取1.1版目录下所有excel
		String[] list=new File("D:\\works\\数据修复工作\\02卖出修复\\成品数据1.1\\09卖出清单").list();
		for(int i=0;i<list.length;i++){
			//原excel名称
			String oldFileName = list[i];
			//生成的sheet页名称
			String newSheetName = list[i].substring(0,list[i].length()-7);
			
			//老excel路径
			String oldPath = "D:\\works\\数据修复工作\\02卖出修复\\成品数据1.1\\09卖出清单\\"+oldFileName;
			for(int j=1;j<=30;j++){
				String day = "";
				if(j<10){
					day = "0"+j;
				}else{
					day = ""+j;
				}
				
				String newPath = "D:\\works\\数据修复工作\\02卖出修复\\成品数据1.2\\9月\\"+day+".xlsx";
				
				XSSFCopySheetUtil t = new XSSFCopySheetUtil();  
		        t.copyWbSheet(oldPath,newPath, day, newSheetName);
			}
			
		}*/
		
		
		
		String[] list=new File("D:\\works\\数据修复工作\\02卖出修复\\成品数据1.2\\9月").list();
		
		for(int i=0;i<list.length;i++){
			String fileName = list[i];
			String path = "D:\\works\\数据修复工作\\02卖出修复\\成品数据1.2\\9月\\"+fileName;
			
			try {
				XSSFWorkbook srcwb = new XSSFWorkbook(new FileInputStream(path));
				srcwb.removeSheetAt(0);
				ds.fileWrite(path,srcwb);
				srcwb.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
    }  
	
    // destSheetName为null时候使用sheetName的值为destFileName的sheet名  
    public void copyWbSheet(String srcfileName, String destFileName,  
            String sheetName, String destSheetName) throws Exception {  
        File srcFile = new File(srcfileName);  
        if (!srcFile.exists()) {  
            return;  
        }  
        XSSFWorkbook srcwb = new XSSFWorkbook(new FileInputStream(srcfileName));  
        XSSFSheet srcSheet = null;  
        if (sheetName == null) {  
            srcSheet = srcwb.getSheetAt(0);  
            sheetName = srcwb.getSheetName(0);  
        } else {  
            srcSheet = srcwb.getSheet(sheetName);  
        }  
        if (srcSheet == null) {  
            return;  
        }  
        if (destSheetName == null) {  
            destSheetName = sheetName;  
        }  
        XSSFWorkbook destwb = new XSSFWorkbook();  
        XSSFSheet destSheet = null;  
        File destFile = new File(destFileName);  
        // 不存在则新建  
        if (!destFile.exists()) {  
            destFile.createNewFile();  
        } else {  
            destwb = new XSSFWorkbook(new FileInputStream(destFile));  
        }  
        destSheet = destwb.getSheet(destSheetName);  
        if (destSheet == null) {  
            destSheet = destwb.createSheet(destSheetName);  
        }  
        // 最大列数  
        int maxCellNum = copySheet(srcSheet, destSheet, srcwb, destwb);  
        // 设置列宽  
        setSheetWidth(srcSheet, destSheet, maxCellNum);  
        // 合并单元格  
        mergeSheetAllRegion(srcSheet, destSheet);  
        // 保存  
        saveFile(destwb, destFileName);  
        
        
        srcwb.close();
        destwb.close();
    }  
  
    public void saveFile(XSSFWorkbook destwb, String destFileName) {  
        try {  
            FileOutputStream fileOutStream = new FileOutputStream(destFileName);  
            destwb.write(fileOutStream);  
            if (fileOutStream != null) {  
                fileOutStream.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public int copySheet(XSSFSheet srcSheet, XSSFSheet destSheet,  
            XSSFWorkbook srcwb, XSSFWorkbook destwb) throws Exception {  
        int rowCount = srcSheet.getLastRowNum();// 总行数  
        int maxCellNum = 0;  
        // System.out.println("------total row=------" + rowCount + "---="+  
        // srcSheet.getPhysicalNumberOfRows());  
        XSSFRow srcRow = null, destRow = null;  
         //注意这里  
                 for (int i = 0; i <=rowCount; i++) {  
            srcRow = srcSheet.getRow(i);  
            destRow = destSheet.getRow(i);  
            if (srcRow == null) {  
                continue;  
            }  
            // 最大列数  
            maxCellNum = maxCellNum < srcRow.getLastCellNum() ? srcRow  
                    .getLastCellNum() : maxCellNum;  
            if (destRow == null) {  
                destRow = destSheet.createRow(i);  
            }  
            // 设置行高  
            destRow.setHeight(srcRow.getHeight());  
            // System.out.println("---------row=" + i + "---="+  
            // srcRow.getPhysicalNumberOfCells() +  
            // "----="+srcRow.getLastCellNum());  
            copySheetRow(srcRow, destRow, srcwb, destwb);  
            srcRow = null;  
            destRow = null;  
        }  
        srcRow = null;  
        destRow = null;  
        return maxCellNum;  
    }  
  
    private void copySheetRow(XSSFRow srcRow, XSSFRow destRow,  
            XSSFWorkbook srcwb, XSSFWorkbook destwb) {  
        int cellCount = srcRow.getLastCellNum();// 每行的总列数  
        XSSFCell srcCell = null, destCell = null;  
        XSSFCellStyle srcCellStyle = null, destCellStyle = null;  
        for (int j = 0; j < cellCount; j++) {// 遍历行单元格  
            srcCell = srcRow.getCell(j);  
            destCell = destRow.getCell(j);  
            if (destCell == null) {  
                destCell = destRow.createCell(j);  
            }  
            if (srcCell != null) {  
                srcCellStyle = srcCell.getCellStyle();// 原sheet页样式  
                destCellStyle = null;  
                destCellStyle = destCell.getCellStyle();  
                // 复制样式  
                destCellStyle.cloneStyleFrom(srcCellStyle);  
                // 处理单元格内容  
                switch (srcCell.getCellType()) {  
                case XSSFCell.CELL_TYPE_STRING:  
                    destCell.setCellValue(srcCell.getRichStringCellValue());  
                    break;  
                // 这里判断是否是日期  
                case XSSFCell.CELL_TYPE_NUMERIC:  
                    // 判断是否是日期格式  
                    // 测试发现如果这里不新建样式,日期显示的是数字  
                    if (DateUtil.isCellDateFormatted(srcCell)) {  
                        // 新建样式  
                        destCellStyle = destwb.createCellStyle();  
                        // 复制样式  
                        destCellStyle.cloneStyleFrom(srcCellStyle);  
                        destCell.setCellStyle(destCellStyle);  
                        destCell.setCellValue(srcCell.getDateCellValue());  
                    } else {  
                        destCell.setCellValue(srcCell.getNumericCellValue());  
                    }  
                    break;  
                case XSSFCell.CELL_TYPE_FORMULA:  
                    destCell.setCellFormula(srcCell.getCellFormula());  
                    break;  
                case XSSFCell.CELL_TYPE_BOOLEAN:  
                    destCell.setCellValue(srcCell.getBooleanCellValue());  
                    break;  
                case XSSFCell.CELL_TYPE_BLANK:  
                    destCell.setCellType(XSSFCell.CELL_TYPE_BLANK);  
                    break;  
                case XSSFCell.CELL_TYPE_ERROR:  
                    break;  
                default:  
                    break;  
                }  
            }  
        }  
        srcCellStyle = null;  
        destCellStyle = null;  
        srcCell = null;  
        destCell = null;  
    }  
  
    public void mergeSheetAllRegion(XSSFSheet srcSheet, XSSFSheet destSheet) {  
        int num = srcSheet.getNumMergedRegions();  
        CellRangeAddress cellR = null;  
        for (int i = 0; i < num; i++) {  
            cellR = srcSheet.getMergedRegion(i);  
            destSheet.addMergedRegion(cellR);  
        }  
    }  
  
    public void setSheetWidth(XSSFSheet srcSheet, XSSFSheet destSheet,  
            int maxCellNum) {  
        for (int i = 0; i <= maxCellNum; i++) {  
            destSheet.setColumnWidth(i, srcSheet.getColumnWidth(i));  
        }  
    }
    public void fileWrite(String targetFile,XSSFWorkbook wb) throws Exception{
        FileOutputStream fileOut = new FileOutputStream(targetFile); 
        wb.write(fileOut); 
        fileOut.flush(); 
        fileOut.close(); 
    }
}
