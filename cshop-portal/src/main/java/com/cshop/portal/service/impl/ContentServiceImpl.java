package com.cshop.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.utils.HttpClientUtil;
import com.cshop.common.utils.JsonUtils;
import com.cshop.pojo.TbContent;
import com.cshop.portal.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	@Override
	public String getContentList() {
		String result = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
		try {
			CshopResult cshopresult = CshopResult.formatToList(result, TbContent.class);
			List<TbContent> list =(List<TbContent>) cshopresult.getData();
			List<Map> resultList = new ArrayList<>();
			for(TbContent tbcontent:list) {
				Map map = new HashMap<>();
				map.put("src", tbcontent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbcontent.getPic2());
				map.put("heightB", 240);
				map.put("widthB", 550);
				map.put("href", tbcontent.getUrl());
				map.put("alt", tbcontent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
