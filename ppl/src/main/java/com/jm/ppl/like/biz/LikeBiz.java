package com.jm.ppl.like.biz;

import com.jm.ppl.like.vo.LikeVO;

public interface LikeBiz {
	
	public boolean addLike(LikeVO likeVO);
	public boolean removeLike(String userId, String targetId);
	public int countLikeByTargetId(String targetId);
	public LikeVO getOneLike(String userId, String targetId);
	
}
