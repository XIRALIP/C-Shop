package com.cshop.service;

import java.util.List;

import com.cshop.common.pojo.EUTreeNode;

public interface ItemCatService {
	List<EUTreeNode> getCatList(long parentId);

}
