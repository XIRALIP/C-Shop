package com.cshop.rest.service;

import java.util.List;

import com.cshop.pojo.TbContent;

public interface ContentService {
	List<TbContent> getContentList(long contentCid);

}
