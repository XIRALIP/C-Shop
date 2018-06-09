package com.cshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUTreeNode;
import com.cshop.service.ContentCatgoryService;

@Controller
@RequestMapping("/content/category")
public class ContenCatgoryController {
	@Autowired
	private ContentCatgoryService cantentCatgoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EUTreeNode> list = cantentCatgoryService.getCategoryList(parentId);
		return list;	
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public CshopResult createContentCat(Long parentId,String name) {
		CshopResult result = cantentCatgoryService.insertContenCatgory(parentId, name);
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public CshopResult deleteContentCat(Long id) {
		CshopResult result = cantentCatgoryService.deleteContentCatgory(id);
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public CshopResult updateContenCatgory(Long id,String name) {
		CshopResult result = cantentCatgoryService.updateContenCatgory(id, name);
		return result;
	}
	
}
