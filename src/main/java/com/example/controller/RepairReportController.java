
package com.example.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.M_Contract;
import com.example.entity.M_Inventory;
import com.example.entity.M_Product;
import com.example.entity.M_User;
import com.example.form.TempReports;
import com.example.service.RepairingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/repair_report")
public class RepairReportController {
	/***/
	private final RepairingService service;

	/**
	 * repair_report/homeのレスポンス
	 * @param model
	 * @param user 認証ユーザ情報
	 * @return repair_report/home のhtml
	 * */
	@GetMapping("/home")
	public String getHome(Model model, @AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);
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
		model.addAttribute("initialContractId", contractList.get(0).getId());
		model.addAttribute("productList", productList);
		/*入力情報保持用フォームをmodelに格納*/
		model.addAttribute("tempReports", service.getTempReports());
		return "repair_report/home";
	}

	/**
	 *repairingHomeFunc.js上で活用する処理
	 *html上で選択した契約Idから該当契約の対象製品のリストを生成する。
	 *@param selectedContractId home.html上で選択した契約Id
	 *@return List<M_Product> 選択した契約Idの対象製品のリスト
	 * */
	@GetMapping("/getProductsByContractId")
	@ResponseBody
	public List<M_Product> getProductsByContractId(@RequestParam("selectedContractId") Integer selectedContractId) {
		return service.getListFilteredByContractId(selectedContractId);
	}

	/**
	 *repairingHomeFunc.js上で活用する処理
	 *html上で入力した依頼日に、選択した契約LeadTimeを加算して納期日を算出する処理
	 *@param LocalDate  requestDate home.html上で入力した依頼日
	 *@param Integer selectedContractId home.html上で選択した契約日
	 *@return LocalDate 納期日(deadLineDate)
	 * */
	@GetMapping("/calcDeadLineDateByContractId")
	@ResponseBody 
	public LocalDate calcDeadLineDate(
			@RequestParam("requestDate") LocalDate requestDate,
			@RequestParam("selectedContractId") Integer selectedContractId) {

		//selectedContractIdに応じたdeadLineDateを返す処理
		Integer result = service.getLeadTimeById(selectedContractId);
		return requestDate.plusDays(result);
	}

	/**
	 *repair_report/usedItemsReportのレスポンス（post）
	 *@param temp
	 *前画面（home.html）からtempRepairingFormの引き継ぎ・保持
	 *
	 * */
	@PostMapping("/usedItemsReport")
	public String postUsedItemsReport(Model model,
			@ModelAttribute("tempReports")TempReports tempReports ,
			@AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);

		/*インベントリ一覧取得*/
		List<M_Inventory> inventoryList = service.getInventoryList();
		model.addAttribute("inventoryList", inventoryList);

//		//確認中
//		System.out.println("①-----");
//		System.out.println("tempReports" + tempReports.toString());

		return "repair_report/usedItemsReport";
	}

	/**
	 *usedItemsReportFunc.js上で活用する処理
	 *html上で入力された図番がm_invenory上に存在するか確認し、
	 *存在する場合は、該当レコードを返し、
	 *存在しない場合はOptional.empty()を返す
	 *@param inputItemNo 画面上で入力した図番。
	 *@return Optional<M_Inventory> 引数の図番を持つレコードor Optional.empty()
	 * */
	@GetMapping("/getInventoryDetails")
	@ResponseBody
	public Optional<M_Inventory> getInventoryDetails(@RequestParam("inputItemNo") String inputItemNo) {
		return service.findItemByItemNo(inputItemNo);
	}

	/**
	 *repair_report/checkReportsのレスポンス（post）
	 *@param temp
	 *前画面（home.html）からtempRepairingFormの引き継ぎ・保持
	 *
	 * */
	@PostMapping("/checkReports")
	public String postCheckReport(Model model,
			@ModelAttribute("tempReports") TempReports tempReports,
			@AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);

		//確認中
		System.out.println("②-----");
		System.out.println("tempReports" + tempReports.toString());
		return "repair_report/checkReports";
	}

	@PostMapping("/scrapItemsReport")
	public String getScrapItemsReport() {
		return "repair_report/scrapItemsReport";
	}

	@PostMapping("/estimate")
	public String getEstimate() {
		return "repair_report/estimate";
	}
	
	@GetMapping("/test")
	@ResponseBody
	public TempReports getTempReportsElement(@RequestParam("tempReports")TempReports tempReports) {
		System.out.println("--------------------");
		System.out.println("called from usedItemsReportFunc.ajax");		
		System.out.println("--------------------");
		System.out.println(tempReports);
		return tempReports;
	};

}
