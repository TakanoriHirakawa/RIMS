package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.M_Inventory;
import com.example.form.InventoryForm;
import com.example.form.TempRecevingItemList;
import com.example.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

	private final InventoryService inventoryService;

	/**
	 * inventory/inventoryのレスポンス
	//	 * @param model
	//	 * @param user 認証ユーザ情報
	 * @return inventory/inventory のhtml
	 * */
	@PostMapping("/inventory")
	public String getInventory(Model model, @AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("initialUserName", user.getUsername());

		List<M_Inventory> list = inventoryService.getInventoryList();

		model.addAttribute("inventoryList", list);

		return "inventory/inventory";
	}

	/**
	 * inventory/inventory.htmlから物品の詳細画面へ遷移するレスポンス
	 * @param model
	 * @param user ：認証ユーザ情報
	 * @param itemNo：選択した物品の図番
	 * @return inventory/inventory_details.html
	 * */
	@GetMapping("/Inventory/itemDetails/{itemNo}")
	public String getItemDetails(Model model, @AuthenticationPrincipal User user,
			@PathVariable String itemNo) {

		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("initialUserName", user.getUsername());

		Optional<M_Inventory> findResult = inventoryService.findRecodeByItemNo(itemNo);

		model.addAttribute("itemDetails", findResult.get());
		return "inventory/inventory_details";
	}

	/**
	 * inventory/regisiter_itemのレスポンス
	 * @param model
	 * @param user ：認証ユーザ情報
	 * @return inventory/register_item.html
	 * */
	@PostMapping("/register_item")
	public String postRegisterItem(Model model, @AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("initialUserName", user.getUsername());

		model.addAttribute("products", inventoryService.getProductList());
		model.addAttribute("initialProductsId", 1);
		model.addAttribute("itemDetails", new InventoryForm());

		return "inventory/register_item";
	}
	
	/**
	 * inventory/regisiter_itemのレスポンス
	 * @param model
	 * @param user ：認証ユーザ情報
	 * @return inventory/register_item.html
	 * */
	@GetMapping("/register_item")
	public String getRegisterItem(Model model, @AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("initialUserName", user.getUsername());

		model.addAttribute("products", inventoryService.getProductList());
		model.addAttribute("initialProductsId", 1);
		model.addAttribute("itemDetails", new InventoryForm());

		return "inventory/register_item";
	}


	/**
	 * 入力内容をDBに登録する処理
	 * @param form：画面上confirmのアラート入力結果
	 * @return nextPage
	 * */
	@PostMapping("/register_item/registItem")
	public String registItem(Model model, @ModelAttribute("itemDetails") InventoryForm form,RedirectAttributes redirectAttributes) {
		form.setStock(0);
		
		inventoryService.registItem(form);
		
		redirectAttributes.addFlashAttribute("itemDetails", form);
		return "redirect:/inventory/register_item?redirect=true";

	};

	/**
	 * register_itemFunc.jsのajax
	 * 次のページを表示する処理
	 * @param ans：画面上confirmのアラート入力結果
	 * @return nextPage
	 * */
	@PostMapping("/nextPageAfterRegistItem")
	@ResponseBody
	public String nextPageAfterRegistItem(@RequestParam("ans") Boolean ans) {
		if (ans) {
			return "/inventory/register_item";
		} else
			return "/inventory/inventory";
	};
	
	/**
	 * 発注の処理
	 * */
	@PostMapping("/order_items")
	public String getOrderItems() {
		return "inventory/order_items";
	}
	
	/**
	 *inventory/receiving_itemsのGETリクエストを処理
	 *@param model
	 *@param user：ログイン認証user情報
	 *@return  inventory/register_items
	 * */
	@GetMapping("/receiving_items")
	public String getReceivingItems(Model model, @AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);
		
		log.info(inventoryService.createNewRecevingItemsList().toString());
		
		/*入庫情報入力フォームの生成（MAX50件）*/
		model.addAttribute("tempRecevingItemList",inventoryService.createNewRecevingItemsList());
		
		return "inventory/receiving_items";
	}
	
	/**
	 * inventory/receiving_items/resistReceivingItems
	 * @param model
	 * @param user
	 * 
	 * */
	@PostMapping("/receiving_items")
	public String postResistReceivingItems(Model model, @AuthenticationPrincipal User user,
			@ModelAttribute("tempRecevingItemList")TempRecevingItemList tempRecevingItemList) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);
		
		inventoryService.resistRecevingItems(tempRecevingItemList,user);
		
		return "inventory/receiving_items";
	}
	
		
	/**
	 * TODO；エラーページの処理を一つにまとめる
	 * ERRORを拾った際に、共通エラーページを表示するメソッド
	 * @param e：エラー
	 * @param model ：springFrameWork model
	 * @return error/error
	 * */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー：ExceptionHandler");
        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "SignupControllerでExceptionが発生しました");
        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error/error";
    }

	

}
