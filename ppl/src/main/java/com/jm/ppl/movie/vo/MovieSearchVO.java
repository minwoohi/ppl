package com.jm.ppl.movie.vo;

import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;

public class MovieSearchVO {

	public Pager pager;

	private String movieId;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public Pager getPager() {

		if (pager == null) {
			pager = PagerFactory.getPager(Pager.ORACLE, 10, 10);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
