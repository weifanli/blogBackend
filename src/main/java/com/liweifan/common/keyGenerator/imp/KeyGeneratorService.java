package com.liweifan.common.keyGenerator.imp;

import java.lang.management.ManagementFactory;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.liweifan.common.keyGenerator.KeyGenerator;

@Service
public class KeyGeneratorService implements KeyGenerator<String>{
	protected String pid;
	
	protected Random random;
	
	public KeyGeneratorService(){
		initPidStr();
		random=new SecureRandom();
		random.setSeed((new Date()).getTime());
	}
	
	protected void initPidStr(){
		pid = ManagementFactory.getRuntimeMXBean().getName();  
        int indexOf = pid.indexOf('@');  
        if (indexOf > 0)  
        {  
            pid = pid.substring(0, indexOf);  
        }
        pid=Long.toHexString(Long.valueOf(pid));
        while(pid.length()<4){
        	pid=0+pid;
        }
	}

	@Override
	public String getNext(String type) {
		return getTimeString()+getPid()+getThreadId()+getRandomNumber(); 
	}
	
	@Override
	public String getNext() {
		return getTimeString()+getPid()+getThreadId()+getRandomNumber(); 
	}
	
	protected String  getTimeString(){
		Long now = (new Date()).getTime();
		return Long.toHexString(now).toUpperCase();
	}
	
	protected String getThreadId(){
		return  String.format("%04d", Thread.currentThread().getId());    
	}
	
	protected String getPid(){
		return pid;
	}
	
	protected String  getRandomNumber(){
		 return String.format("%02d",random.nextInt(100))+String.format("%03d",random.nextInt(1000));
	}
}
