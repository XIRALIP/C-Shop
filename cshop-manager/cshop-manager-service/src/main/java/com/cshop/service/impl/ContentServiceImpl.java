package com.cshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.pojo.EUDataGridResult;
import com.cshop.common.pojo.EUTreeNode;
import com.cshop.common.utils.HttpClientUtil;
import com.cshop.mapper.TbContentMapper;
import com.cshop.pojo.TbContent;
import com.cshop.pojo.TbContentExample;
import com.cshop.pojo.TbContentExample.Criteria;
import com.cshop.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	@Override
	public EUDataGridResult getContentList(long categoryId, int page, int rows) {
		TbContentExample example = new TbContentExample();
		PageHelper.startPage(page, rows);
		
		List<TbContent> list = contentMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		for(TbContent content:list) {
			if(content.getCategoryId() == categoryId) {
				result.setRows(list);
				PageInfo<TbContent> pageinfo = new PageInfo<>(list);
				result.setTotal(pageinfo.getTotal());
				return result;
			}
		}
		return result;
		
	}
	@Override
	public CshopResult insertContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		try {
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return CshopResult.ok();
	}

}
