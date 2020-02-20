package com.liweifan.common.json.entity;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StandardDateFormat extends SimpleDateFormat {
	private static final long serialVersionUID = 1L;
	public final static String FORMAT_DEFAULT="yyyy-MM-dd HH:mm:ss";

	public StandardDateFormat(){
		super(FORMAT_DEFAULT);
	}

	
	@Override
	public Date parse(String source, ParsePosition pos) {
		// TODO Auto-generated method stub
		if (source!=null&&source.trim().length() == 10) { 
			source+=" 00:00:00";
		}
		return super.parse(source, pos);
	}

}
