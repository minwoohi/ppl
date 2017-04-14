package com.jm.ppl.itemPost.vo;

import com.jm.ppl.items.vo.ItemsVO;

public class ItemPostVO {
	private String itemPostId;
	private String itemId;
	private String postName;
	
	private ItemsVO item;
	
	public String getItemPostId() {
		return itemPostId;
	}
	public void setItemPostId(String itemPostId) {
		this.itemPostId = itemPostId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public ItemsVO getItem() {
		if(item == null){
			item = new ItemsVO();
		}
		return item;
	}
	public void setItem(ItemsVO item) {
		this.item = item;
	}
	
}
