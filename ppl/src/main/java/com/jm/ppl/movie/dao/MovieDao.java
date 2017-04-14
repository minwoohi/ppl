package com.jm.ppl.movie.dao;

import java.util.List;

import com.jm.ppl.movie.vo.MovieSearchVO;
import com.jm.ppl.movie.vo.MovieVO;

public interface MovieDao {

	// C
	public int insertNewMovie(MovieVO movieVO);

	// R
	public List<MovieVO> selectAllMovies(MovieSearchVO movieSearchVO); // LIST
	public MovieVO selectOneMovie(String movieId); // LIST 

	// U
	public int updateOneMovie(MovieVO movieVO);

	// D
	public int deleteOneMovie(String movieId);

	// pagerCount
	public int selectAllMoviesCount(MovieSearchVO movieSearchVO);
	
	// addLikeCount
	public int addLikeCount(String movieId);
	
	public int setLike(int likeCount, String movieId);

}
