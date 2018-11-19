package com.putai.index.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@RestController
@CrossOrigin()
public class IndexController {

	@Value("file:config/newsData.json")
	private Resource news;
	
	@GetMapping("/news")
	public List<New> getNews() {
		return Util.read(news);
	}
	
	@PostMapping("/news")
	public void saveNew(@RequestBody JSONObject jsonObject) {
		String title = jsonObject.getString("title");
		String url = jsonObject.getString("url");
		List<New> list = Util.read(news);
		New item=new New();
		item.setTitle(title);
		item.setUrl(url);
		list.add(item);
		Util.write( JSON.toJSONString(list));
	}
}
