package com.jm.ppl.items.service;

import java.util.List;

import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.items.vo.ItemsVO;

public interface ItemsService {
	public List<ItemsVO> getAllItems(ItemsSearchVO itemSearchVO);
	public ItemsVO getOneItem(String itemId);
	public boolean addItem(ItemsVO itemVO);
	public boolean removeItem(String itemId);
	public boolean modifyItem(ItemsVO itemVO);
	public List<ItemsVO> getAllitemsByIds(ItemsSearchVO itemSearchVO);
	public boolean plusLikeCount(String itemId);
	public boolean minusLikeCount(String itemId);
	public boolean setLike(int likeCount, String itemId);
}
