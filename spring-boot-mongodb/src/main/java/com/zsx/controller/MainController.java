package com.zsx.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class MainController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping("add")
	@ResponseBody
	public JSONObject add(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		

		Map<String, String[]> parameterMap = request.getParameterMap();
		
		String collectionName = "";
		Document document = new Document();
		
		for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue()[0];
			
			if ("collection".equals(key)) {
				collectionName = value;
			}else{
				document.append(key, value);
			}
			
		}

		if (!"".equals(collectionName)) {
			mongoTemplate.insert(document, collectionName);
			jsonObject.put("success", true);
			jsonObject.put("msg", "插入成功");
		}else{
			jsonObject.put("success", false);
			jsonObject.put("msg", "请指定一个集合插入数据");
		}
		return jsonObject;
		
		
//		List<Object> find2 = mongoTemplate.find(new Query(null), Object.class, "data");
	}

	
	@RequestMapping("get")
	@ResponseBody
	public List<Object> get(HttpServletRequest request) {
		
		String collection = request.getParameter("collection");
		
		if (collection == null) {
			return null;
		}
		Criteria criteria = new Criteria();
		
		List<Object> list = mongoTemplate.find(new Query(criteria), Object.class, collection);
		
		return list;
	}
	
}
