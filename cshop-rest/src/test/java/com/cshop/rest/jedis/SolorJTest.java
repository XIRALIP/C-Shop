package com.cshop.rest.jedis;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolorJTest {
	@Test
	public void addDocument() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8090/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id","test001");
		document.addField("item_title", "测试商品2");
		document.addField("item_price",12345666);
		solrServer.add(document);
		solrServer.commit();
	}
	@Test
	public void delDocument() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8090/solr");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
}
