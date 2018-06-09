package com.cshop.httpclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.cshop.common.pojo.CshopResult;
import com.cshop.common.utils.HttpClientUtil;
import com.cshop.common.utils.JsonUtils;
import com.cshop.pojo.TbContent;

public class HttpClientTest {

	@Test
	public void doGet() throws Exception {
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个GET对象
		HttpGet get = new HttpGet("http://www.sogou.com");
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doGetWithParam() throws Exception{
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
		uriBuilder.addParameter("query", "花千骨");
		HttpGet get = new HttpGet(uriBuilder.build());
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doPost() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		//创建一个post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.action");
		
		List<NameValuePair> kvlist = new ArrayList<>();
		kvlist.add(new BasicNameValuePair("username", "admin"));
		kvlist.add(new BasicNameValuePair("password", "123456"));
		
		StringEntity entity = new UrlEncodedFormEntity(kvlist,"utf-8");
		
		post.setEntity(entity);
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
		
	}
	
	@Test
	public void doPostWithParam() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
	 
		//创建一个post对象  
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.action");
		//创建一个Entity。模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "张三"));
		kvList.add(new BasicNameValuePair("password", "123"));
		
		//包装成一个Entity对象 
		StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
		//设置请求的内容 
		post.setEntity(entity);
		
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
	}
	
	@Test
	public void testtt() throws Exception{
		String result = HttpClientUtil.doGet("http://localhost:8081/rest/content/list/89");
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
			System.out.println(JsonUtils.objectToJson(resultList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("null");
	}
}
