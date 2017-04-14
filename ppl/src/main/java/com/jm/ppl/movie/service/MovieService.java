package com.jm.ppl.movie.service;

import java.util.List;

import com.jm.ppl.movie.vo.MovieSearchVO;
import com.jm.ppl.movie.vo.MovieVO;

public interface MovieService {
		// C
		public boolean addNewMovie(MovieVO movieVO);

		// R
		public List<MovieVO> getAllMovies(MovieSearchVO movieSearchVO); // LIST
		public MovieVO getOneMovies(String movieId);

		// U
		public boolean updateMovie(MovieVO movieVO);
		
		//D
		public boolean removeOneMovie(String movieId);
		
		// addLikeCount
		public boolean addLikeCount(String movieId);
		
		public boolean setLike(int likeCount, String movieId);

}
