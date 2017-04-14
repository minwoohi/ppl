package com.jm.ppl.drama.service;

import java.util.List;

import com.jm.ppl.drama.biz.DramaBiz;
import com.jm.ppl.drama.biz.DramaBizImpl;
import com.jm.ppl.drama.vo.DramaSearchVO;
import com.jm.ppl.drama.vo.DramaVO;
import com.jm.ppl.like.biz.LikeBiz;
import com.jm.ppl.like.biz.LikeBizImpl;

public class DramaServiceImpl implements DramaService{

private DramaBiz dramaBiz;
private LikeBiz likeBiz;
	
	public DramaServiceImpl() {
		dramaBiz = new DramaBizImpl();
		likeBiz = new LikeBizImpl();
	}
	
	@Override
	public List<DramaVO> getAllDramas(DramaSearchVO dramaSearch) {
		return dramaBiz.getAllDramas(dramaSearch);
	}

	@Override
	public DramaVO getOneDrama(String dramaId) {
		int likeCount = likeBiz.countLikeByTargetId(dramaId);
		DramaVO drama = dramaBiz.getOneDrama(dramaId);
		
		drama.setDramaLikeCount(likeCount);
		return drama;
	}

	@Override
	public boolean addOneDrama(DramaVO drama) {
		return dramaBiz.addOneDrama(drama);
	}

	@Override
	public boolean renewOneDrama(DramaVO drama) {
		return dramaBiz.renewOneDrama(drama);
	}

	@Override
	public boolean removeOneDrama(String dramaId) {
		return dramaBiz.removeOneDrama(dramaId);
	}

	@Override
	public boolean setLike(int likeCount, String dramaId) {
		return dramaBiz.setLike(likeCount, dramaId);
	}

}
