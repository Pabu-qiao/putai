package com.putai.index.controller;


import java.util.List;
import java.util.stream.Collectors;

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
//@CrossOrigin(value= {"http://www.51putai.com"})
@CrossOrigin(origins= {"http://localhost:8080"})
public class IndexController {

	@Value("file:config/newsData.json")
	private Resource news;
	
	private final Long pageSize=10L;
	
	@GetMapping("/news/{page}")
	public List<New> getNews(@PathVariable Integer page) {
		//设置每页10条新闻
		List<New> collect = Util.read(news).stream().skip(((page>0?page:1)-1)*pageSize).limit(pageSize).collect(Collectors.toList());
		return collect;
	}
	
	@GetMapping("/total")
	public Integer	getTotal() {
		Integer total = (int) Math.ceil(Util.read(news).size()/new Double(pageSize));
		return total;
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
