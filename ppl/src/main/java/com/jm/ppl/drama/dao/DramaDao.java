package com.jm.ppl.drama.dao;

import java.util.List;

import com.jm.ppl.drama.vo.DramaSearchVO;
import com.jm.ppl.drama.vo.DramaVO;

public interface DramaDao {
	
	public List<DramaVO> selectAllDramas(DramaSearchVO dramaSearch);
	
	public DramaVO selectOneDrama(String dramaId);
	
	public int insertOneDrama(DramaVO drama);
	
	public int updateOneDrama(DramaVO drama);
	
	public int deleteOneDrama(String dramaId);

	public int getAllArticlesCount(DramaSearchVO dramaSearch);
	
	public int setLike(int likeCount, String dramaId);

}
