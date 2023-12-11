
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/repair_report")
public class RepairReportController {
	
	@GetMapping("/home")
	public String getHome() {
		return "repair_report/home";
	}
	
	@PostMapping("/homePost")
	public String postHome() {
		return "repair_report/home";
	}
	
	@PostMapping("/usedItemsReport")
	public String getUsedParts() {
		return "repair_report/usedItemsReport";
	}
	
	@PostMapping("/details")
	public String getDetails() {
		return "repair_report/details";
	}

	@PostMapping("/checkReport")
	public String getCheckReport() {
		return "repair_report/checkReport";
	}
	
	@PostMapping("/scrapItemsReport")
	public String getScrapItemsReport() {
		return "repair_report/scrapItemsReport";
	}
	
	@PostMapping("/estimate")
	public String getEstimate() {
		return "repair_report/estimate";
	}
	
}
