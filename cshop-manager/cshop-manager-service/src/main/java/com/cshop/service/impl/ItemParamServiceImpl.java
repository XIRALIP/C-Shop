package com.cshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUDataGridResult;
import com.cshop.common.pojo.EUTreeNode;
import com.cshop.mapper.TbItemParamItemMapper;
import com.cshop.mapper.TbItemParamMapper;
import com.cshop.pojo.TbItem;
import com.cshop.pojo.TbItemExample;
import com.cshop.pojo.TbItemParam;
import com.cshop.pojo.TbItemParamExample;
import com.cshop.pojo.TbItemParamExample.Criteria;
import com.cshop.service.ItemParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public CshopResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		
		List<TbItemParam> list  = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list !=null && list.size() >0) {
			return CshopResult.ok(list.get(0));
		}
		return CshopResult.ok();
	}
	@Override
	public CshopResult insertItemParam(TbItemParam itemparam) {
		itemparam.setCreated(new Date());
		itemparam.setUpdated(new Date());
		itemParamMapper.insert(itemparam);
		return CshopResult.ok();
	}
	@Override
	public EUDataGridResult getItemParamList(int page,int rows) {
		TbItemParamExample example = new TbItemParamExample();
		
		PageHelper.startPage(page, rows);
		
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
