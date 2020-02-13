package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * commentsテーブルの情報操作をするリポジトリです.
 * 
 * @author sanihiro
 *
 */
/**
 * commentsテーブルの情報操作をするリポジトリ.
 * 
 * @author sanihiro
 *
 */
@Repository
public class CommentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<Comment> COMMENT_ROW_MAPPER = (rs,i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};
	
	/**
	 * commentsの全件検索をします.
	 * 
	 * @return commentsの全件
	 */
	public List<Comment> findAll(){
		String sql = "SELECT id,name,content,article_id FROM comments ORDER BY id DESC ;";
		
		return template.query(sql, COMMENT_ROW_MAPPER);
	}
	
	/**
	 * 引数でもらったコメント情報をDB上に追加します.
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments (name,content,article_id) VALUES (:name,:content,:articleId) ;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		
		template.update(sql, param);
	}
	
	/**
	 * 引数でもらったidで行を指定してその行を消します.
	 * 
	 * @param id id
	 */
	public void deleteByArticleId(int id) {
		String sql = "DELETE FROM comments WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(sql, param);
	}
	
}
