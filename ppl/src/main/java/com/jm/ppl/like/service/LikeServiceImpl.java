package com.jm.ppl.like.service;

import com.jm.ppl.like.biz.LikeBiz;
import com.jm.ppl.like.biz.LikeBizImpl;
import com.jm.ppl.like.vo.LikeVO;

public class LikeServiceImpl implements LikeService {

	private LikeBiz likeBiz;
	
	public LikeServiceImpl() {
		likeBiz = new LikeBizImpl();
	}
	
	@Override
	public boolean addLike(LikeVO likeVO) {
		return likeBiz.addLike(likeVO);
	}

	@Override
	public boolean removeLike(String userId, String targetId) {
		return likeBiz.removeLike(userId, targetId);
	}

	@Override
	public int countLikeByTargetId(String targetId) {
		return likeBiz.countLikeByTargetId(targetId);
	}

	@Override
	public LikeVO getOneLike(String userId, String targetId) {
		return likeBiz.getOneLike(userId, targetId);
	}

}
