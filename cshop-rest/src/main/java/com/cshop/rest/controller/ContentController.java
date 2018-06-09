package com.cshop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.utils.ExceptionUtil;
import com.cshop.pojo.TbContent;
import com.cshop.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCatgoryId}")
	@ResponseBody
	public CshopResult getContentList(@PathVariable Long contentCatgoryId) {
		try {
			List<TbContent> list = contentService.getContentList(contentCatgoryId);
			return CshopResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return CshopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
}
