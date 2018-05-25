package com.cshop.controller;

import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cshop.service.ItemParamItemService;

@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/show/item/{itemId}")
	public String showItemParam(@PathVariable Long itemId,Model model) {
		String string = itemParamItemService.getItemParamById(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}
}
