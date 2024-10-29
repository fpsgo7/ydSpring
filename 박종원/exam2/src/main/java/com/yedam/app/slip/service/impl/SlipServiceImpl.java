package com.yedam.app.slip.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.slip.mapper.SlipMapper;
import com.yedam.app.slip.service.Slip;
import com.yedam.app.slip.service.SlipService;
@Service
public class SlipServiceImpl implements SlipService{

	@Autowired	SlipMapper dao;
	
	@Override
	public List<String> insertSlip(List<Slip> slips) {
		List<String> employeeIds = new ArrayList();
		for (Slip slip : slips) {
			if(slip.getSlipAmount() <= 20000) {
				dao.insertSlip(slip);
			}else {
				String customer = slip.getCustomer();
				String employeeIdString = customer.split("_")[0];
				employeeIds.add(employeeIdString);
			}
		}
		
		return employeeIds;  // 처리 건수 리턴;
	}

}
