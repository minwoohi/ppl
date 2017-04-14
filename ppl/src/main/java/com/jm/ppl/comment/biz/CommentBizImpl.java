package com.jm.ppl.comment.biz;

import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.comment.dao.CommentDao;
import com.jm.ppl.comment.dao.CommentDaoImpl;
import com.jm.ppl.comment.vo.CommentSearchVO;
import com.jm.ppl.comment.vo.CommentVO;
import com.jm.ppl.drama.vo.DramaVO;

public class CommentBizImpl implements CommentBiz{

	private CommentDao commentDao;
	
	public CommentBizImpl() {
		commentDao = new CommentDaoImpl();
	}
	
	@Override
	public List<CommentVO> getAllComments(CommentSearchVO commentSearch) {
		int commentCount = commentDao.getAllArticlesCount(commentSearch);
		
		commentSearch.getPager().setTotalArticleCount(commentCount);
		
		if(commentCount == 0){
			return new ArrayList<CommentVO>(); // 기사 없다면 새 리스트 출력
		}else{
			return commentDao.selectAllComments(commentSearch); // 기사 있다면 dao 통해 기사 조회
		}
	}

	@Override
	public CommentVO getOneComment(String commentId) {
		return commentDao.selectOneComment(commentId);
	}

	@Override
	public boolean addOneComment(CommentVO comment) {
		return commentDao.insertOneComment(comment) > 0;
	}

	@Override
	public boolean renewOneComment(CommentVO comment) {
		return commentDao.updateOneComment(comment) > 0;
	}

	@Override
	public boolean removeOneComment(String commentId) {
		return commentDao.deleteOneComment(commentId) > 0;
	}

}
