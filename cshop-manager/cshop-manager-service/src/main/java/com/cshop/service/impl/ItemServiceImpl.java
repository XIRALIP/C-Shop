package com.cshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUDataGridResult;
import com.cshop.common.utils.IDUtils;
import com.cshop.mapper.TbItemDescMapper;
import com.cshop.mapper.TbItemMapper;
import com.cshop.mapper.TbItemParamItemMapper;
import com.cshop.mapper.TbItemParamMapper;
import com.cshop.pojo.TbItem;
import com.cshop.pojo.TbItemDesc;
import com.cshop.pojo.TbItemExample;
import com.cshop.pojo.TbItemExample.Criteria;
import com.cshop.pojo.TbItemParamItem;
import com.cshop.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired 
	private TbItemParamItemMapper itemParamItemMapper; 
	
	@Override
	public TbItem getItemById(long itemId) {
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list =  itemMapper.selectByExample(example);
		if(list !=null && list.size()>0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		
		PageHelper.startPage(page, rows);
		
		List<TbItem> list = itemMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	@Override
	public CshopResult createItem(TbItem item,String desc,String itemParam) throws Exception{
		
		Long itemId=IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		
		CshopResult result =  insertItemDesc(itemId,desc);
		if(result.getStatus()!=200) {
			throw new Exception();
		}
		 result =  insertItemParam(itemId,itemParam);
		if(result.getStatus()!=200) {
			throw new Exception();
		}
		return CshopResult.ok();
	}
	
	private CshopResult insertItemParam(Long itemId,String itemParam) {
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(itemParamItem);
		return CshopResult.ok();
	}
	
	private CshopResult insertItemDesc(Long itemId,String desc) {
		TbItemDesc itemDsec = new TbItemDesc();
		itemDsec.setItemId(itemId);
		itemDsec.setItemDesc(desc);
		itemDsec.setCreated(new Date());
		itemDsec.setUpdated(new Date());
		itemDescMapper.insert(itemDsec);
		
		return CshopResult.ok();
	}


}
