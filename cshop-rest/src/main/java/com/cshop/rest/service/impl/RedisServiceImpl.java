package com.cshop.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.utils.ExceptionUtil;
import com.cshop.rest.dao.JedisClient;
import com.cshop.rest.service.RedisService;
@Service
public class RedisServiceImpl implements RedisService{
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	@Override
	public CshopResult syncContent(long contentCid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			
		} catch (Exception e) {
			e.printStackTrace();
			return CshopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return CshopResult.ok();
	}

}
