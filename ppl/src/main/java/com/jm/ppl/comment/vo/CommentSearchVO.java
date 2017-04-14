package com.jm.ppl.comment.vo;

import com.jm.ppl.common.web.pager.Pager;

public class CommentSearchVO {
	private Pager pager;
	private String itemId;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
}
