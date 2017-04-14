package com.jm.ppl.admin.likecount.service;

import java.util.List;

import com.jm.ppl.admin.likecount.vo.ActorVO;
import com.jm.ppl.admin.likecount.vo.DramaVO;
import com.jm.ppl.admin.likecount.vo.ItemVO;
import com.jm.ppl.admin.likecount.vo.MovieVO;

public interface LikeCountService {

	public List<ActorVO> viewLikeCount();
	public List<MovieVO> viewMovieLikeCount();
	public List<DramaVO> viewDramaLikeCount();
	public List<ItemVO> viewItemLikeCount();
}
