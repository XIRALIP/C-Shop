package com.cshop.service;

import java.util.List;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUDataGridResult;
import com.cshop.common.pojo.EUTreeNode;
import com.cshop.pojo.TbItemParam;

public interface ItemParamService {
	EUDataGridResult getItemParamList(int page,int rows);
	CshopResult getItemParamByCid(long cid);
	CshopResult insertItemParam(TbItemParam itemparam);

}
