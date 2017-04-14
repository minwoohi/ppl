package com.jm.ppl.comment.biz;

import java.util.List;

import com.jm.ppl.comment.vo.CommentSearchVO;
import com.jm.ppl.comment.vo.CommentVO;

public interface CommentBiz {
	public List<CommentVO> getAllComments(CommentSearchVO commentSearch);
	
	public CommentVO getOneComment(String commentId);
	
	public boolean addOneComment(CommentVO comment);
	
	public boolean renewOneComment(CommentVO comment);
	
	public boolean removeOneComment(String commentId);
	
}
