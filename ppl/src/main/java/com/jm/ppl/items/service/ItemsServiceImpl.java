package com.jm.ppl.items.service;

import java.util.List;

import com.jm.ppl.items.biz.ItemsBiz;
import com.jm.ppl.items.biz.ItemsBizImpl;
import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.like.biz.LikeBiz;
import com.jm.ppl.like.biz.LikeBizImpl;

public class ItemsServiceImpl implements ItemsService {

	private ItemsBiz itemsBiz;
	private LikeBiz likeBiz;
	
	public ItemsServiceImpl() {
		itemsBiz = new ItemsBizImpl();
		likeBiz = new LikeBizImpl();
	}
	
	@Override
	public List<ItemsVO> getAllItems(ItemsSearchVO itemSearchVO) {
		return itemsBiz.getAllItems(itemSearchVO);
	}

	@Override
	public ItemsVO getOneItem(String itemId) {
		int likeCount = likeBiz.countLikeByTargetId(itemId);
		ItemsVO item = itemsBiz.getOneItem(itemId);
		
		item.setItemLikeCount(likeCount);
		return item;
	}

	@Override
	public boolean addItem(ItemsVO itemVO) {
		return itemsBiz.addItem(itemVO);
	}

	@Override
	public boolean removeItem(String itemId) {
		return itemsBiz.removeItem(itemId);
	}

	@Override
	public boolean modifyItem(ItemsVO itemVO) {
		return itemsBiz.modifyItem(itemVO);
	}

	@Override
	public List<ItemsVO> getAllitemsByIds(ItemsSearchVO itemSearchVO) {
		return itemsBiz.getAllitemsByIds(itemSearchVO);
	}

	@Override
	public boolean plusLikeCount(String itemId) {
		return itemsBiz.plusLikeCount(itemId);
	}

	@Override
	public boolean minusLikeCount(String itemId) {
		return itemsBiz.minusLikeCount(itemId);
	}

	@Override
	public boolean setLike(int likeCount, String itemId) {
		return itemsBiz.setLike(likeCount, itemId);
	}

}
