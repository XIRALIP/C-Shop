package com.cshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUDataGridResult;
import com.cshop.pojo.TbContent;
import com.cshop.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/save")
	@ResponseBody
	public CshopResult insertContent(TbContent content) {
		CshopResult result =  contentService.insertContent(content);
		return result;
	}
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContent(Long categoryId,Integer page,Integer rows) {
		EUDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}
}
