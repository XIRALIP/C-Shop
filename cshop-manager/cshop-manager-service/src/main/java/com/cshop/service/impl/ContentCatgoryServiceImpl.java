package com.cshop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUTreeNode;
import com.cshop.mapper.TbContentCategoryMapper;
import com.cshop.pojo.TbContentCategory;
import com.cshop.pojo.TbContentCategoryExample;
import com.cshop.pojo.TbContentCategoryExample.Criteria;
import com.cshop.service.ContentCatgoryService;
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {
	@Autowired
	private TbContentCategoryMapper contetCategoryMapper;
	@Override
	public List<EUTreeNode> getCategoryList(long parent) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parent);
		List<TbContentCategory> list = contetCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for(TbContentCategory tbcatogory:list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbcatogory.getId());
			node.setText(tbcatogory.getName());
			node.setState(tbcatogory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
	@Override
	public CshopResult insertContenCatgory(long parentId, String name) {
		TbContentCategory contentCatgory = new TbContentCategory();
		contentCatgory.setName(name);
		contentCatgory.setIsParent(false);
		contentCatgory.setStatus(1);
		contentCatgory.setParentId(parentId);
		contentCatgory.setSortOrder(1);
		contentCatgory.setCreated(new Date());
		contentCatgory.setUpdated(new Date());
		
		contetCategoryMapper.insert(contentCatgory);
		
		TbContentCategory parentCat =  contetCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			contetCategoryMapper.updateByPrimaryKey(parentCat);
		}
		
		return CshopResult.ok(contentCatgory);
	}
	@Override
	public CshopResult deleteContentCatgory(long id) {
		
		contetCategoryMapper.deleteByPrimaryKey(id);
		
		
//		TbContentCategory parentCat =  contetCategoryMapper.selectByPrimaryKey(id);
//		if(parentCat==null) {
//			parentCat.setIsParent(false);
//			contetCategoryMapper.updateByPrimaryKey(parentCat);
//		}
		
		return CshopResult.ok();
	}
	@Override
	public CshopResult updateContenCatgory(long id, String name) {
		TbContentCategory contentCatgory = new TbContentCategory();
		contentCatgory.setId(id);
		contentCatgory.setName(name);
		contentCatgory.setUpdated(new Date());
		
		contetCategoryMapper.updateByPrimaryKey(contentCatgory);
		return CshopResult.ok();
	}

}
