package com.jm.ppl.actor.dao;

import java.util.List;

import com.jm.ppl.actor.vo.ActorSearchVO;
import com.jm.ppl.actor.vo.ActorVO;

public interface ActorDao {
	
	public List<ActorVO> selectAllActors(ActorSearchVO actorSearchVO);
	public ActorVO selectOneActor(String actorId); 
	public int insertActor(ActorVO actorVO);
	public int deleteActor(String actorId);
	public int updateActor(ActorVO actorVO);
	public int selectAllActorsCount(ActorSearchVO actorSearchVO);
	public int plusUpdateLikeCount(String actorId);
	public int minusUpdateLikeCount(String actorId);
	public int setLike(int likeCount, String actorId);
	
}
