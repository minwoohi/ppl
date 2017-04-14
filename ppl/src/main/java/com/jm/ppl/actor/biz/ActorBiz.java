package com.jm.ppl.actor.biz;

import java.util.List;

import com.jm.ppl.actor.vo.ActorSearchVO;
import com.jm.ppl.actor.vo.ActorVO;

public interface ActorBiz {

	public List<ActorVO> getAllActors(ActorSearchVO actorSearchVO);
	public ActorVO getOneActor(String actorId);
	public boolean addActor(ActorVO actorVO);
	public boolean removeActor(String actorId);
	public boolean modifyActor(ActorVO actorVO);
	public boolean plusLikeCount(String actorId);
	public boolean minusLikeCount(String actorId);
	public boolean setLike(int likeCount, String actorId);
}
