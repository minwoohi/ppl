package com.jm.ppl.itemPost.dao;

import java.util.List;

import com.jm.ppl.itemPost.vo.ItemPostVO;

public interface ItemPostDao {
	public List<ItemPostVO> selectAllPosts(String itemId);
	
	public ItemPostVO selectOnePost(String itemPostId);
	
	public int insertOnePost(ItemPostVO item);
	
	public int deleteOnePost(String itemPostId);
}
