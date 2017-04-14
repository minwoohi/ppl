package com.jm.ppl.actor.service;

import java.util.List;

import com.jm.ppl.actor.biz.ActorBiz;
import com.jm.ppl.actor.biz.ActorBizImpl;
import com.jm.ppl.actor.vo.ActorSearchVO;
import com.jm.ppl.actor.vo.ActorVO;
import com.jm.ppl.like.biz.LikeBiz;
import com.jm.ppl.like.biz.LikeBizImpl;

public class ActorServiceImpl implements ActorService {

	private ActorBiz actorBiz;
	private LikeBiz likeBiz;
	
	public ActorServiceImpl() {
		actorBiz = new ActorBizImpl();
		likeBiz = new LikeBizImpl();
	}
	
	@Override
	public List<ActorVO> getAllActors(ActorSearchVO actorSearchVO) {
		return actorBiz.getAllActors(actorSearchVO);
	}

	@Override
	public ActorVO getOneActor(String actorId) {
		int likeCount = likeBiz.countLikeByTargetId(actorId);
		ActorVO actor = actorBiz.getOneActor(actorId);
		
		actor.setActorLikeCount(likeCount);
		return actor;
	}

	@Override
	public boolean addActor(ActorVO actorVO) {
		return actorBiz.addActor(actorVO);
	}

	@Override
	public boolean removeActor(String actorId) {
		return actorBiz.removeActor(actorId);
	}

	@Override
	public boolean modifyActor(ActorVO actorVO) {
		return actorBiz.modifyActor(actorVO);
	}

	@Override
	public boolean plusLikeCount(String actorId) {
		return actorBiz.plusLikeCount(actorId);
	}

	@Override
	public boolean minusLikeCount(String actorId) {
		return actorBiz.minusLikeCount(actorId);
	}

	@Override
	public boolean setLike(int likeCount, String actorId) {
		return actorBiz.setLike(likeCount, actorId);
	}

	
	
}
