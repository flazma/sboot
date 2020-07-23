package com.flazma.starter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flazma.starter.dto.Article;
import com.flazma.starter.service.ArticleService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ArticleController {
		
	
	@Autowired
	ArticleService articleService;
	
	
	@RequestMapping("/article/list")
	public String showList(Model aModel) {
		
		List<Article> list = articleService.getList();
		int totalCount = articleService.getTotalCount();
		
		aModel.addAttribute("list",list);
		aModel.addAttribute("totalCount",totalCount);
		
		return "article/list";
	}
	
	
	@RequestMapping("/article/add")
	public String showAdd() {
		
		return "article/add";
	}
	
	@RequestMapping("/article/doAdd")
	@ResponseBody
	public String doAdd(@RequestParam Map<String,Object> param) {
		
		param.get("title");
		
		long id = articleService.add(param);
		String msg = id + "번 게시물이추가되었습니다.";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<script>");
		sb.append("alert('" + msg + "');" );
		//sb.append("location.replace('./detail?id=" + id +"');" );
		sb.append("location.replace('./list');" );
		sb.append("</script>");
		
		return sb.toString();
	}
	
	
	@RequestMapping("/article/detail")
	public String showDetail(Model model,long id) {
			
		
		Article article = articleService.getOne(id);
		
		model.addAttribute("article",article);
				
		return "article/detail";
	}
	
}
