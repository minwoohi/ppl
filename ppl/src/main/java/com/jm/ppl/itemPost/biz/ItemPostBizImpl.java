package com.jm.ppl.itemPost.biz;

import java.util.List;

import com.jm.ppl.itemPost.dao.ItemPostDao;
import com.jm.ppl.itemPost.dao.ItemPostDaoImpl;
import com.jm.ppl.itemPost.vo.ItemPostVO;

public class ItemPostBizImpl implements ItemPostBiz{

	private ItemPostDao itemPostDao;
	
	public ItemPostBizImpl() {
		itemPostDao = new ItemPostDaoImpl();
	}
	
	@Override
	public List<ItemPostVO> getAllPosts(String itemId) {
		return itemPostDao.selectAllPosts(itemId);
	}
	
	@Override
	public ItemPostVO getOnePost(String itemPostId) {
		return itemPostDao.selectOnePost(itemPostId);
	}

	@Override
	public boolean addOnePost(ItemPostVO item) {
		return itemPostDao.insertOnePost(item) > 0;
	}

	@Override
	public boolean removeOnePost(String itemPostId) {
		return itemPostDao.deleteOnePost(itemPostId) > 0;
	}

}
