package com.jm.ppl.comment.service;

import java.util.List;

import com.jm.ppl.comment.biz.CommentBiz;
import com.jm.ppl.comment.biz.CommentBizImpl;
import com.jm.ppl.comment.vo.CommentSearchVO;
import com.jm.ppl.comment.vo.CommentVO;

public class CommentServiceImpl implements CommentService {

	private CommentBiz commentBiz;
	
	public CommentServiceImpl() {
		commentBiz = new CommentBizImpl();
	}
	
	@Override
	public List<CommentVO> getAllComments(CommentSearchVO commentSearch) {
		return commentBiz.getAllComments(commentSearch);
	}

	@Override
	public CommentVO getOneComment(String commentId) {
		return commentBiz.getOneComment(commentId);
	}

	@Override
	public boolean addOneComment(CommentVO comment) {
		return commentBiz.addOneComment(comment);
	}

	@Override
	public boolean renewOneComment(CommentVO comment) {
		return commentBiz.renewOneComment(comment);
	}

	@Override
	public boolean removeOneComment(String commentId) {
		return commentBiz.removeOneComment(commentId);
	}

}
