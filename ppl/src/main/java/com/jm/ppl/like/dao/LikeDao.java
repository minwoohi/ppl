package com.jm.ppl.like.dao;

import com.jm.ppl.like.vo.LikeVO;

public interface LikeDao {

	public int insertLike(LikeVO likeVO);
	public int deleteLike(String userId, String targetId);
	public int countLikeByTargetId(String targetId);
	public LikeVO selectOneLike(String userId, String targetId);
	
}
