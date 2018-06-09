package com.cshop.service;

import java.util.List;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUTreeNode;

public interface ContentCatgoryService {
	CshopResult insertContenCatgory(long parentId,String name);
	CshopResult deleteContentCatgory(long id);
	List<EUTreeNode> getCategoryList(long parent);
	
	CshopResult updateContenCatgory(long id,String name);

}
