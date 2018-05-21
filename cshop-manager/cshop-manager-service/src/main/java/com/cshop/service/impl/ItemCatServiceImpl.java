package com.cshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.common.pojo.EUTreeNode;
import com.cshop.mapper.TbItemCatMapper;
import com.cshop.pojo.TbItemCat;
import com.cshop.pojo.TbItemCatExample;
import com.cshop.pojo.TbItemCatExample.Criteria;
import com.cshop.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria creiteria = example.createCriteria();
		creiteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> resultList =new ArrayList<>();
		for(TbItemCat tbItemCat:list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		
		return resultList;
	}

}
