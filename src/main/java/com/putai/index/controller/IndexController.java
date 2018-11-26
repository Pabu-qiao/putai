package com.putai.index.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@RestController
@CrossOrigin(value= {"http://www.51putai.com","http://172.16.241.166:8080","http://47.99.156.9:8080"})
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

	@DeleteMapping("/news/{title}")
	public void deleteNew(@PathVariable String title) {
		Util.delete(news, title);
	}
}
