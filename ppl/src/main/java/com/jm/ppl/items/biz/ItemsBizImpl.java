package com.jm.ppl.items.biz;

import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.items.dao.ItemsDao;
import com.jm.ppl.items.dao.ItemsDaoImpl;
import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.items.vo.ItemsVO;

public class ItemsBizImpl implements ItemsBiz {
	
	private ItemsDao itemDao;
	
	public ItemsBizImpl(){
		itemDao = new ItemsDaoImpl();
	}

	@Override
	public List<ItemsVO> getAllItems(ItemsSearchVO itemSearchVO) {
		
		int itemCount =  itemDao.selectAllItemsCount(itemSearchVO);
		
		Pager pager = itemSearchVO.getPager();
		pager.setTotalArticleCount(itemCount);
		if (itemCount == 0) {
			return new ArrayList<ItemsVO>();
		}
		return itemDao.selectAllItems(itemSearchVO);
	}

	@Override
	public ItemsVO getOneItem(String itemId) {
		return itemDao.selectOneItem(itemId);
	}

	@Override
	public boolean addItem(ItemsVO itemVO) {
		return itemDao.insertItem(itemVO) > 0;
	}

	@Override
	public boolean removeItem(String itemId) {
		return itemDao.deleteItem(itemId) > 0;
	}

	@Override
	public boolean modifyItem(ItemsVO itemVO) {
		return itemDao.updateItem(itemVO) > 0;
	}

	@Override
	public List<ItemsVO> getAllitemsByIds(ItemsSearchVO itemSearchVO) {

		/*System.out.println(itemSearchVO.getActorId());
		System.out.println(itemSearchVO.getDramaId());
		System.out.println(itemSearchVO.getMovieId());*/
		
		int itemCount =  itemDao.selectAllItemsCount(itemSearchVO);
				
		Pager pager = itemSearchVO.getPager();
		pager.setTotalArticleCount(itemCount);
		if (itemCount == 0) {
			return new ArrayList<ItemsVO>();
		}
		
		else if ( itemSearchVO.getActorId() != null ) {
			//System.out.println(itemSearchVO.getActorId());
			List<ItemsVO> itemList = itemDao.selectItemsByActorId(itemSearchVO);
			return itemList;
		}
		else if ( itemSearchVO.getMovieId() != null ) {
			return itemDao.selectItemsByMovieId(itemSearchVO);
		}
		else if ( itemSearchVO.getDramaId() != null ) {
			System.out.println("dramaId 호출");
			return itemDao.selectItemsByDramaId(itemSearchVO);
		}
		else {
			return new ArrayList<ItemsVO>();
		}
	}

	@Override
	public boolean plusLikeCount(String itemId) {
		return itemDao.plusUpdateLikeCount(itemId) > 0;
	}

	@Override
	public boolean minusLikeCount(String itemId) {
		return itemDao.minusUpdateLikeCount(itemId) > 0;
	}

	@Override
	public boolean setLike(int likeCount, String itemId) {
		return itemDao.setLike(likeCount, itemId) > 0;
	}

}
