package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	@PostMapping("/inventory")
	public String getInventory() {
		return "inventory/inventory";
	}
	
	@PostMapping("/order_items")
	public String getOrderItems() {
		return "inventory/order_items";
	}
	
	@PostMapping("/receiving_items")
	public String getReceivingItems() {
		return "inventory/receiving_items";
	}
	
	@PostMapping("/register_item")
	public String getRegisterItem() {
		return "inventory/register_item";
	}
}
