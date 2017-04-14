package com.jm.ppl.like.service;

import com.jm.ppl.like.vo.LikeVO;

public interface LikeService {

	public boolean addLike(LikeVO likeVO);
	public boolean removeLike(String userId, String targetId);
	public int countLikeByTargetId(String targetId);
	public LikeVO getOneLike(String userId, String targetId);
}
