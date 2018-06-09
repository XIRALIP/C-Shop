package com.cshop.rest.service;

import com.cshop.common.pojo.CshopResult;

public interface RedisService {
	CshopResult syncContent(long contentCid);
}
