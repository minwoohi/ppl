package com.jm.ppl.items.vo;

import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;

public class ItemsSearchVO {
	
	private Pager pager;

	private String actorId;
	private String movieId;
	private String dramaId;	
	
	public Pager getPager() {
		if (pager == null) {
			pager = PagerFactory.getPager(Pager.ORACLE);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getDramaId() {
		return dramaId;
	}

	public void setDramaId(String dramaId) {
		this.dramaId = dramaId;
	}

}
