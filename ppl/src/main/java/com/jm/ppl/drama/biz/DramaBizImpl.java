package com.jm.ppl.drama.biz;

import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.drama.dao.DramaDao;
import com.jm.ppl.drama.dao.DramaDaoImpl;
import com.jm.ppl.drama.vo.DramaSearchVO;
import com.jm.ppl.drama.vo.DramaVO;



public class DramaBizImpl implements DramaBiz {

	private DramaDao dramaDao;
	
	public DramaBizImpl() {
		dramaDao = new DramaDaoImpl();
	}
	
	@Override
	public List<DramaVO> getAllDramas(DramaSearchVO dramaSearch) {
		int articleCount = dramaDao.getAllArticlesCount(dramaSearch);
		
		dramaSearch.getPager().setTotalArticleCount(articleCount);
		
		if(articleCount == 0){
			return new ArrayList<DramaVO>(); // 기사 없다면 새 리스트 출력
		}else{
			return dramaDao.selectAllDramas(dramaSearch); // 기사 있다면 dao 통해 기사 조회
		}
	}

	@Override
	public DramaVO getOneDrama(String dramaId) {
		return dramaDao.selectOneDrama(dramaId);
	}

	@Override
	public boolean addOneDrama(DramaVO drama) {
		return dramaDao.insertOneDrama(drama) > 0;
	}

	@Override
	public boolean renewOneDrama(DramaVO drama) {
		return dramaDao.updateOneDrama(drama) > 0;
	}

	@Override
	public boolean removeOneDrama(String dramaId) {
		return dramaDao.deleteOneDrama(dramaId) > 0;
	}

	@Override
	public boolean setLike(int likeCount, String dramaId) {
		return dramaDao.setLike(likeCount, dramaId) > 0;
	}

}
