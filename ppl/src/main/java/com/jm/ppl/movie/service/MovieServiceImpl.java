package com.jm.ppl.movie.service;

import java.util.List;

import com.jm.ppl.like.biz.LikeBiz;
import com.jm.ppl.like.biz.LikeBizImpl;
import com.jm.ppl.movie.biz.MovieBiz;
import com.jm.ppl.movie.biz.MovieBizImpl;
import com.jm.ppl.movie.vo.MovieSearchVO;
import com.jm.ppl.movie.vo.MovieVO;

public class MovieServiceImpl implements MovieService {
	
	public MovieBiz movieBiz;
	private LikeBiz likeBiz;
	
	public MovieServiceImpl(){
		movieBiz = new MovieBizImpl();
		likeBiz = new LikeBizImpl();
	}

	@Override
	public boolean addNewMovie(MovieVO movieVO) {
		return movieBiz.addNewMovie(movieVO);
	}

	@Override
	public List<MovieVO> getAllMovies(MovieSearchVO movieSearchVO) {
		return movieBiz.getAllMovies(movieSearchVO);
	}

	@Override
	public MovieVO getOneMovies(String movieId) {
		int likeCount = likeBiz.countLikeByTargetId(movieId);
		MovieVO movie = movieBiz.getOneMovies(movieId);
		
		movie.setMovieLikeCount(likeCount);
		return movie;
	}

	@Override
	public boolean updateMovie(MovieVO movieVO) {
		return movieBiz.updateMovie(movieVO);
	}

	@Override
	public boolean removeOneMovie(String movieId) {
		return movieBiz.removeOneMovie(movieId);
	}

	@Override
	public boolean addLikeCount(String movieId) {
		return movieBiz.addLikeCount(movieId);
	}

	@Override
	public boolean setLike(int likeCount, String movieId) {
		return movieBiz.setLike(likeCount, movieId);
	}

}
