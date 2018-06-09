package com.cshop.service;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUDataGridResult;
import com.cshop.pojo.TbContent;

public interface ContentService {
	EUDataGridResult getContentList(long categoryId,int page,int rows);
	CshopResult insertContent(TbContent content);

}
