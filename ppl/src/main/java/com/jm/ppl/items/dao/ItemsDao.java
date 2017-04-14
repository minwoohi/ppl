package com.jm.ppl.items.dao;

import java.util.List;

import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.items.vo.ItemsVO;

public interface ItemsDao {
	
	public List<ItemsVO> selectAllItems(ItemsSearchVO itemSearchVO);
	public ItemsVO selectOneItem(String itemId);
	public int insertItem(ItemsVO itemVO);
	public int deleteItem(String itemId);
	public int updateItem(ItemsVO itemVO);
	public int selectAllItemsCount(ItemsSearchVO itemSearchVO);
	public List<ItemsVO> selectItemsByActorId(ItemsSearchVO itemSearchVO);
	public List<ItemsVO> selectItemsByMovieId(ItemsSearchVO itemSearchVO);
	public List<ItemsVO> selectItemsByDramaId (ItemsSearchVO itemSearchVO);
	public int plusUpdateLikeCount(String itemId);
	public int minusUpdateLikeCount(String itemId);
	public int setLike(int likeCount, String itemId);

}
