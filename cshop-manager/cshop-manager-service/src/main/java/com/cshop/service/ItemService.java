package com.cshop.service;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUDataGridResult;
import com.cshop.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	
	EUDataGridResult getItemList(int page,int rows);
	
	CshopResult createItem(TbItem item,String desc,String itemParam) throws Exception;
}
