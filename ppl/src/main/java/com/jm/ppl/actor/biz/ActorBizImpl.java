package com.jm.ppl.actor.biz;

import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.actor.dao.ActorDao;
import com.jm.ppl.actor.dao.ActorDaoImpl;
import com.jm.ppl.actor.vo.ActorSearchVO;
import com.jm.ppl.actor.vo.ActorVO;
import com.jm.ppl.common.web.pager.Pager;

public class ActorBizImpl implements ActorBiz {
	
	private ActorDao actorDao;

	public ActorBizImpl() {
		actorDao = new ActorDaoImpl();
	}
	
	@Override
	public List<ActorVO> getAllActors(ActorSearchVO actorSearchVO) {
		
		int actorCount = actorDao.selectAllActorsCount(actorSearchVO);
		
		Pager pager = actorSearchVO.getPager();
		pager.setTotalArticleCount(actorCount);
		
		if ( actorCount == 0 ) {
			return new ArrayList<ActorVO>();
		}
		return actorDao.selectAllActors(actorSearchVO);
	}

	@Override
	public ActorVO getOneActor(String actorId) {
		return actorDao.selectOneActor(actorId);
	}

	@Override
	public boolean addActor(ActorVO actorVO) {
		return actorDao.insertActor(actorVO) > 0;
	}

	@Override
	public boolean removeActor(String actorId) {
		return actorDao.deleteActor(actorId) > 0;
	}

	@Override
	public boolean modifyActor(ActorVO actorVO) {
		//System.out.println(actorVO.getActorId());
		return actorDao.updateActor(actorVO) > 0;
	}

	@Override
	public boolean plusLikeCount(String actorId) {
		return actorDao.plusUpdateLikeCount(actorId) > 0;
	}

	@Override
	public boolean minusLikeCount(String actorId) {
		// TODO Auto-generated method stub
		return actorDao.minusUpdateLikeCount(actorId) > 0;
	}

	@Override
	public boolean setLike(int likeCount, String actorId) {
		return actorDao.setLike(likeCount, actorId) > 0;
	}

}
