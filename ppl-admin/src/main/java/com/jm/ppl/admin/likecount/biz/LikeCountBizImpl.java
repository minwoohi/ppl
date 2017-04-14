package com.jm.ppl.admin.likecount.biz;

import java.util.List;

import com.jm.ppl.admin.likecount.dao.LikeCountDao;
import com.jm.ppl.admin.likecount.dao.LikeCountDaoimpl;
import com.jm.ppl.admin.likecount.vo.ActorVO;
import com.jm.ppl.admin.likecount.vo.DramaVO;
import com.jm.ppl.admin.likecount.vo.ItemVO;
import com.jm.ppl.admin.likecount.vo.MovieVO;

public class LikeCountBizImpl implements LikeCountBiz{

	
	LikeCountDao countDao;
	public LikeCountBizImpl() {
		countDao = new LikeCountDaoimpl();
	}
	
	@Override
	public List<ActorVO> viewLikeCount() {
		
		return countDao.selectActorLikeCount();
	}

	@Override
	public List<MovieVO> viewMovieLikeCount() {
		
		return countDao.selectMovieLikeCount();
	}

	@Override
	public List<DramaVO> viewDramaLikeCount() {
		
		return countDao.selectDramaLikeCount();
	}

	@Override
	public List<ItemVO> viewItemLikeCount() {
		
		return countDao.selectItemLikeCount();
	}
	
	
	
	

}
