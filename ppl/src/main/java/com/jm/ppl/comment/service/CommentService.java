package com.jm.ppl.comment.service;

import java.util.List;

import com.jm.ppl.comment.vo.CommentSearchVO;
import com.jm.ppl.comment.vo.CommentVO;

public interface CommentService {
	public List<CommentVO> getAllComments(CommentSearchVO commentSearch);

	public CommentVO getOneComment(String commentId);

	public boolean addOneComment(CommentVO comment);

	public boolean renewOneComment(CommentVO comment);

	public boolean removeOneComment(String commentId);
}
