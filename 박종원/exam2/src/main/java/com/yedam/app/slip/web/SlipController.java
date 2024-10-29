package com.yedam.app.slip.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.slip.service.Slip;
import com.yedam.app.slip.service.SlipService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
@Controller
public class SlipController {
	
	@Autowired
	SlipService slipService;
	
	private static final Logger logger = LoggerFactory.getLogger(SlipController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//급여신청 처리 핸들러 작성
	//주문처리 핸들러 작성
	@PostMapping("slips")
	@ResponseBody
	public Map<String, Object> slips(@RequestBody List<Slip> list) {
		Map<String, Object> map = new HashMap<String, Object>();
//		for (Slip slip : list) {
//			System.out.println(slip);
//		}
		List<String> employeeIds = slipService.insertSlip(list);
		map.put("total", list.size());
		map.put("failCount", employeeIds.size());
		map.put("result","true");
		map.put("empList", employeeIds);
		return map;
	}
}
