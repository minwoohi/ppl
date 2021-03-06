package com.jm.ppl.itemPost.service;

import java.util.List;

import com.jm.ppl.itemPost.vo.ItemPostVO;

public interface ItemPostService {
	
	public List<ItemPostVO> getAllPosts(String itemId);
	
	public ItemPostVO getOnePost(String itemPostId);

	public boolean addOnePost(ItemPostVO item);

	public boolean removeOnePost(String itemPostId);
}
