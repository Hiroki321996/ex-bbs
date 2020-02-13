package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

/**
 * 記事・コメント投稿の情報制御をおこなうコントローラークラス.
 * 
 * @author sanihiro
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	
	@Autowired
	private ArticleRepository repository;
	
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articles = repository.findAll();
		model.addAttribute("articles", articles);
		return "bbs";
	}
	
	@RequestMapping("/insertArticle")
	public String insertArticle(Model model,String name,String content) {
		Article article = new Article();
		article.setName(name);
		article.setContent(content);
		repository.insert(article);
		
		return index(model);
	}
}
