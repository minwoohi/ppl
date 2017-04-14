package com.jm.ppl.admin.likecount.service;

import java.util.List;

import com.jm.ppl.admin.likecount.biz.LikeCountBiz;
import com.jm.ppl.admin.likecount.biz.LikeCountBizImpl;
import com.jm.ppl.admin.likecount.vo.ActorVO;
import com.jm.ppl.admin.likecount.vo.DramaVO;
import com.jm.ppl.admin.likecount.vo.ItemVO;
import com.jm.ppl.admin.likecount.vo.MovieVO;

public class LikeCountServiceImpl implements LikeCountService{

	LikeCountBiz countBiz;
	public LikeCountServiceImpl() {
		countBiz = new LikeCountBizImpl();
	}
	
	@Override
	public List<ActorVO> viewLikeCount() {
		
		return countBiz.viewLikeCount();
	}

	@Override
	public List<MovieVO> viewMovieLikeCount() {
		
		return countBiz.viewMovieLikeCount();
	}

	@Override
	public List<DramaVO> viewDramaLikeCount() {
		
		return countBiz.viewDramaLikeCount();
	}

	@Override
	public List<ItemVO> viewItemLikeCount() {
		
		return countBiz.viewItemLikeCount();
	}

}
