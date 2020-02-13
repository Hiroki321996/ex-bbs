package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

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
	private ArticleRepository repositoryA;
	
	@Autowired
	private CommentRepository repositoryC;
	
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articles = repositoryA.findAll();
		
		List<Comment> commentList = new ArrayList<Comment>();
		for(Article article: articles) {
			commentList = repositoryC.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}
		
		model.addAttribute("articles", articles);
		
		return "bbs";
	}
	
	/**
	 * 記事投稿者名と記事内容を受け取って、bbs上に反映させます.
	 * 
	 * @param name 記事投稿者名
	 * @param content　記事内容
	 * @return　bbsページ
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(String name,String content) {
		if(name.equals("") || content.equals("")) {
			return "redirect:/bbs";
		}
		
		Article article = new Article();
		article.setName(name);
		article.setContent(content);
		repositoryA.insert(article);
		
		return "redirect:/bbs";
	}
	
	/**
	 * コメント者名とコメント内容をもらって、bbs上に反映させます.
	 * 
	 * @param name コメント者名
	 * @param content　コメント内容
	 * @param articleId　記事のid
	 * @return bbsページ
	 */
	@RequestMapping("/insertComment")
	public String insertComment(String name,String content,String articleId) {
		if(name.equals("") || content.equals("")) {
			return "redirect:/bbs";
		}
		
		Comment comment = new Comment();
		comment.setName(name);
		comment.setContent(content);
		int intArticleId = Integer.parseInt(articleId);
		comment.setArticleId(intArticleId);
		repositoryC.insert(comment);
		
		return "redirect:/bbs";
	}
	
	/**
	 * 記事を削除します.
	 * 
	 * @param id 記事id
	 * @return bbsページ
	 */
	@RequestMapping("/deleteArticle")
	public String deleteArticle(int id) {
		repositoryA.deleteById(id);
		return "redirect:/bbs";
	}
}
