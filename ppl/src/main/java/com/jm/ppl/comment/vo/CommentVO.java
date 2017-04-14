package com.jm.ppl.comment.vo;

import com.jm.ppl.items.vo.ItemsVO;

public class CommentVO {
	private String commentId;
	private String itemId;
	private String comment;
	private String date;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	private ItemsVO item;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
