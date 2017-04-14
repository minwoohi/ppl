package com.jm.ppl.like.biz;

import com.jm.ppl.like.dao.LikeDao;
import com.jm.ppl.like.dao.LikeDaoImpl;
import com.jm.ppl.like.vo.LikeVO;

public class LikeBizImpl implements LikeBiz {

	private LikeDao likeDao;
	
	public LikeBizImpl() {
		likeDao = new LikeDaoImpl();
	}
	
	@Override
	public boolean addLike(LikeVO likeVO) {
		return likeDao.insertLike(likeVO) > 0;
	}

	@Override
	public boolean removeLike(String userId, String targetId) {
		return likeDao.deleteLike(userId, targetId) > 0;
	}

	@Override
	public int countLikeByTargetId(String targetId) {
		return likeDao.countLikeByTargetId(targetId);
	}

	@Override
	public LikeVO getOneLike(String userId, String targetId) {
		return likeDao.selectOneLike(userId, targetId);
	}

}
