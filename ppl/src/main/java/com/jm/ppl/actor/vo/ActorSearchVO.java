package com.jm.ppl.actor.vo;

import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;

public class ActorSearchVO {
	
	private Pager pager;

	public Pager getPager() {
		
		if (pager == null) {
			pager = PagerFactory.getPager(Pager.ORACLE);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
