package com.cshop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cshop.common.pojo.CshopResult;
import com.cshop.rest.service.RedisService;

@Controller
@RequestMapping("/cache/sync")
public class ReadisController {
	@Autowired
	private RedisService redisService;
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public CshopResult contentCachSync(@PathVariable Long contentCid) {
		CshopResult result = redisService.syncContent(contentCid);
		return result;
	}
}
