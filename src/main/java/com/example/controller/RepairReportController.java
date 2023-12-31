
package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.M_Contract;
import com.example.entity.M_Product;
import com.example.entity.M_User;
import com.example.form.TempRepairingForm;
import com.example.service.RepairingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/repair_report")
public class RepairReportController {
	/***/
	private final RepairingService service;

	@GetMapping("/home")
	public String getHome(Model model,@AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin",isAdmin);
		model.addAttribute("initialUserName", user.getUsername());
		/*ユーザー一覧取得*/
		List<M_User> userList = service.getUserList();
		/*契約一覧取得*/
		List<M_Contract> contractList = service.getContractList();
		/*製品一覧取得*/
		List<M_Product> productList = service.getProductList();
		/*各取得内容をmodelに格納*/
		model.addAttribute("userList", userList);
		model.addAttribute("contractList", contractList);
		model.addAttribute("initialContractId",contractList.get(0).getId());
		model.addAttribute("productList", productList);
		return "repair_report/home";
	}

	@GetMapping("/getProductsByContractId")
	@ResponseBody
	public List<M_Product> getProductsByContractId(@RequestParam("selectedContractId") Integer selectedContractId) {
		return service.getListFilteredByContractId(selectedContractId);
	}

	@GetMapping("/calcDeadLineDateByContractId")
	@ResponseBody
	public LocalDate calcDeadLineDate(
			@RequestParam("requestDate") LocalDate requestDate,
			@RequestParam("selectedContractId") Integer selectedContractId) {

		//selectedContractIdに応じたdeadLineDateを返す処理
		Integer result = service.getLeadTimeById(selectedContractId);
		return requestDate.plusDays(result);
	}

	@PostMapping("/homePost")
	public String postHome() {
		return "repair_report/home";
	}

	@PostMapping("/usedItemsReport")
	public String getUsedParts(Model model,TempRepairingForm form) {
		System.out.println("---FORMの情報---");
		System.out.println(form.toString());
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
