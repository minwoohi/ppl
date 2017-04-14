package com.jm.ppl.admin.likecount.dao;

import java.util.List;

import com.jm.ppl.admin.likecount.vo.ActorVO;
import com.jm.ppl.admin.likecount.vo.DramaVO;
import com.jm.ppl.admin.likecount.vo.ItemVO;
import com.jm.ppl.admin.likecount.vo.MovieVO;

public interface LikeCountDao {

	
	public List<ActorVO> selectActorLikeCount();
	
	public List<DramaVO> selectDramaLikeCount();
	public List<MovieVO> selectMovieLikeCount();
	public List<ItemVO> selectItemLikeCount();
	
	
}
