package com.liweifan.common.keyGenerator.imp;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.liweifan.common.keyGenerator.KeyGenerator;

@Service
public class LongKeyGeneratorService implements KeyGenerator<Long>{
	@Override
	public Long getNext(String type) {
		Date da=new Date();
		return da.getTime();
	}

	@Override
	public Long getNext() {
		Date da=new Date();
		return da.getTime();
	}
}
