package com.cshop.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.mapper.TbItemMapper;
import com.cshop.pojo.TbItem;
import com.cshop.pojo.TbItemExample;
import com.cshop.pojo.TbItemExample.Criteria;
import com.cshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
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

}
