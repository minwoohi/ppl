package com.jm.ppl.comment.dao;

import java.util.List;

import com.jm.ppl.comment.vo.CommentSearchVO;
import com.jm.ppl.comment.vo.CommentVO;

public interface CommentDao {
	public List<CommentVO> selectAllComments(CommentSearchVO commentSearch);
	
	public CommentVO selectOneComment(String commentId);
	
	public int insertOneComment(CommentVO comment);
	
	public int updateOneComment(CommentVO comment);
	
	public int deleteOneComment(String commentId);

	public int getAllArticlesCount(CommentSearchVO commentSearch);
}
