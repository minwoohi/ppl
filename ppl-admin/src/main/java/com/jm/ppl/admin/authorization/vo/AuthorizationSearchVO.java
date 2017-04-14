package com.jm.ppl.admin.authorization.vo;

import com.jm.ppl.admin.common.web.pager.Pager;
import com.jm.ppl.admin.common.web.pager.PagerFactory;

public class AuthorizationSearchVO {
	private Pager pager;

	public Pager getPager() {
		if (pager == null) {
			pager = PagerFactory.getPager(Pager.ORACLE , 1000 ,10); //1000개  한그룹당 10개
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
