package com.example.form;

/**
 * コメントのリクエストパラメータを受け取るフォームクラス.
 * 
 * @author sanihiro
 *
 */
public class CommentForm {

	/** 記事id */
	private String articleId;

	/** コメント投稿者名 */
	private String name;

	/** コメント内容 */
	private String content;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
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

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}

}
