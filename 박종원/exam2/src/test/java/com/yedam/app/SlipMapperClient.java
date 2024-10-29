package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.slip.mapper.SlipMapper;
import com.yedam.app.slip.service.Slip;


@SpringBootTest
public class SlipMapperClient {
	
	@Autowired SlipMapper mapper;
	
	@Test
	public void test() {
		Slip slip = new Slip();
		slip.setSlipNo(2);
		slip.setSlipAmount(200);
		slip.setSalDt("202410");
		slip.setCustomer("201_james");
		slip.setBankAcct("12-124-12");
		int r = mapper.insertSlip(slip);
		System.out.println(r+"건 처리됨");
    }
}
