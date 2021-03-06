package com.jm.ppl.drama.biz;

import java.util.List;

import com.jm.ppl.drama.vo.DramaSearchVO;
import com.jm.ppl.drama.vo.DramaVO;

public interface DramaBiz {

	public List<DramaVO> getAllDramas(DramaSearchVO dramaSearch);
	
	public DramaVO getOneDrama(String dramaId);
	
	public boolean addOneDrama(DramaVO drama);
	
	public boolean renewOneDrama(DramaVO drama);
	
	public boolean removeOneDrama(String dramaId);
	
	public boolean setLike(int likeCount, String dramaId);
}
