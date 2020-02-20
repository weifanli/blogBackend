package com.liweifan.modules.ImportZbms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liweifan.common.utils.StringUtils;
import com.liweifan.modules.ImportZbms.dao.PzBdzbMapper;
import com.liweifan.modules.ImportZbms.entity.PzBdfl;
import com.liweifan.modules.ImportZbms.entity.PzBdzb;

@Controller
@RequestMapping(value="/zbms")
public class ZbmsController {
	@Autowired
	private PzBdzbMapper pzBdzbMapper;
	
	@RequestMapping(value="/mybatisHello")
	@ResponseBody
	public void mybatisHello(){
		List<PzBdzb> selectBdzbList = pzBdzbMapper.selectBdzbList();
		List<Map<String, String>> selectZBK = pzBdzbMapper.selectZBK();
		
		List<PzBdfl> selectBdflList = pzBdzbMapper.selectBdflList();
		
		Map<String,String> bdzbMap = new HashMap<String,String>();
		for (Map<String, String> map : selectZBK) {
			bdzbMap.put(map.get("code"), StringUtils.trim(map.get("zbjc")));
		}
		
		Map<String,String> bdflMap = new HashMap<String,String>();
		for (PzBdfl pzBdfl : selectBdflList) {
			bdflMap.put(pzBdfl.getCode(), StringUtils.trim(pzBdfl.getName()));
		}
		
		int iad = 0;
		for (PzBdzb pzBdzb : selectBdzbList) {
			String jsgs = pzBdzb.getJsgs();
			jsgs = jsgs.replace("@", "");
			String syStr = jsgs;
			String jq1 = "";	//截取的符号
			String jq2 = "";	//截取的编码
			String returnStr="";//返回字符串
			iad++;
			int flag = 0;
			while (syStr.length()>0) {
				int zbIdx = syStr.indexOf("#");
				int upIdx = StringUtils.getUpIdx(syStr);
				if(zbIdx<upIdx && zbIdx>=0){//指标
					jq1=syStr.substring(0,zbIdx+1);
					if(jq1.indexOf("[")>0)flag=1;
					if(jq1.indexOf("]")>0)flag=0;
					jq2=syStr.substring(zbIdx, zbIdx+7);
					syStr = syStr.substring(zbIdx+7, syStr.length());
					returnStr += jq1 +"["+ bdzbMap.get(jq2.substring(1,jq2.length()))+"]";
				}else if(zbIdx>=0 || upIdx >=0){//表单
					jq1=syStr.substring(0,upIdx);
					if(jq1.indexOf("[")>0)flag=1;
					if(jq1.indexOf("]")>0)flag=0;
					if(null != jq1 && jq1.length()>0){
						syStr = syStr.substring(jq1.length(), syStr.length());
					}
					int bdbmIdx = syStr.indexOf(".");
					jq2=syStr.substring(0, bdbmIdx);
					
					syStr = syStr.substring(bdbmIdx, syStr.length());
					returnStr += jq1;
					if(jq2.length()==12 && flag==0){
						String s = jq2.substring(0, 9);
						returnStr +=bdflMap.get(jq2.substring(0, 9))+".";
					}
					returnStr += bdflMap.get(jq2);
				}else{
					returnStr += syStr;
					syStr="";
				}
			}
			System.out.println(iad);
			returnStr = returnStr.replace("#", "");
			pzBdzb.setJsgs(returnStr);
			pzBdzbMapper.updateZb(returnStr, pzBdzb.getId());
		}
	}
}
