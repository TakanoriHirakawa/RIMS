package com.example.util;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.form.TempReports;

//@Controller
//@RequiredArgsConstructor
public class appController {
	
	//private final RepairingService service;
	
	@PostMapping("/util/test")
	@ResponseBody
	public TempReports getTempReportsElement(@RequestParam("tempReports")TempReports tempReports) {
		System.out.println("--------------------");
		System.out.println("called from usedItemsReportFunc.ajax");		
		System.out.println("--------------------");
		System.out.println(tempReports);
		return tempReports;
	};
}