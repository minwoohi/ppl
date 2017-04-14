package com.jm.ppl.itemPost.service;

import java.util.List;

import com.jm.ppl.itemPost.biz.ItemPostBiz;
import com.jm.ppl.itemPost.biz.ItemPostBizImpl;
import com.jm.ppl.itemPost.vo.ItemPostVO;

public class ItemPostServiceImpl implements ItemPostService {

	private ItemPostBiz itemPostBiz;

	public ItemPostServiceImpl() {
		itemPostBiz = new ItemPostBizImpl();
	}

	@Override
	public List<ItemPostVO> getAllPosts(String itemId) {
		return itemPostBiz.getAllPosts(itemId);
	}

	@Override
	public ItemPostVO getOnePost(String itemPostId) {
		return itemPostBiz.getOnePost(itemPostId);
	}
	
	@Override
	public boolean addOnePost(ItemPostVO item) {
		return itemPostBiz.addOnePost(item);
	}

	@Override
	public boolean removeOnePost(String itemPostId) {
		return itemPostBiz.removeOnePost(itemPostId);
	}

}
