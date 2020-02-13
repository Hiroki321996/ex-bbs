package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * articlesテーブルを操作するリポジトリです.
 * 
 * @author sanihiro
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs,i) -> {
		Article article = new Article();
		
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};
	
	/**
	 * articlesから全件検索を行います.
	 * 
	 * @return articlesの全件文の情報
	 */
	public List<Article> findAll(){
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC ;";
		
		return  template.query(sql, ARTICLE_ROW_MAPPER);
	}
	
	/**
	 * もらってきた記事情報をDB上に追加します.
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles (id,name,content) VALUES (:id,:name,:content) ;";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		
		template.update(sql, param);
	}
	
	/**
	 * もらってきた主キーに該当する記事情報を削除します.
	 * 
	 * @param id id
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM articles WHERE id = :id ;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(sql, param);
	}
}
