package com.cshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUDataGridResult;
import com.cshop.pojo.TbItemParam;
import com.cshop.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page,Integer rows) {
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public CshopResult getItemParamByCid(@PathVariable Long itemCatId) {
		CshopResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public CshopResult insertItemParam(@PathVariable Long cid,String paramData) {
	TbItemParam itemParam = new TbItemParam();
	itemParam.setItemCatId(cid);
	itemParam.setParamData(paramData);
	CshopResult result = itemParamService.insertItemParam(itemParam);
	return result;
	}
}
