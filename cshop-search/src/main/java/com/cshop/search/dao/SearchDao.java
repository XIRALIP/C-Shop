package com.cshop.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.cshop.search.pojo.SearchResult;

public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
