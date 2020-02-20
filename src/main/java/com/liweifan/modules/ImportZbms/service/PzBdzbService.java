package com.liweifan.modules.ImportZbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liweifan.modules.ImportZbms.dao.PzBdzbMapper;
import com.liweifan.modules.ImportZbms.entity.PzBdzb;
@Service
public class PzBdzbService {
	@Autowired
	private PzBdzbMapper pzBdzbMapper;
}
