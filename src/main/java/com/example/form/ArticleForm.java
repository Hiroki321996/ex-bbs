package com.example.form;

/**
 * 記事投稿の際に受け取る値を保持するフォームクラスです.
 * 
 * @author sanihiro
 *
 */
public class ArticleForm {

	/** 投稿者名 */
	private String name;

	/** 投稿内容 */
	private String content;
	
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
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
}
