package com.example.domain;

import java.util.List;

/**
 * articlesテーブルの情報を表すドメインクラスです.
 * 
 * @author sanihiro
 *
 */
public class Article {
	
	/** id */
	private Integer id;
	
	/** 記事名　*/
	private String name;
	
	/**　記事内容　*/
	private String content;
	
	/**　記事についているコメント */
	private List<Comment> commentList;

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
}
