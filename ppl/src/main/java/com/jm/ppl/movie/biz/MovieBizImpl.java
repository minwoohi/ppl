package com.jm.ppl.movie.biz;

import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.movie.dao.MovieDao;
import com.jm.ppl.movie.dao.MovieDaoImpl;
import com.jm.ppl.movie.vo.MovieSearchVO;
import com.jm.ppl.movie.vo.MovieVO;

public class MovieBizImpl implements MovieBiz {

	private MovieDao movieDao;

	public MovieBizImpl() {
		movieDao = new MovieDaoImpl();
	}

	@Override
	public boolean addNewMovie(MovieVO movieVO) {
		return movieDao.insertNewMovie(movieVO) > 0;
	}

	@Override
	public List<MovieVO> getAllMovies(MovieSearchVO movieSearchVO) {
		// List 호출시 Pager값도 동시 호출.
		int movieCount = movieDao.selectAllMoviesCount(movieSearchVO);

		System.out.println("movieCount : " + movieCount);

		movieSearchVO.getPager().setTotalArticleCount(movieCount);

		if (movieCount == 0) {
			return new ArrayList<MovieVO>(); // 영화가 없다면 새 리스트 출력.
		} else {
			return movieDao.selectAllMovies(movieSearchVO); // 영화가 있다면 dao를 호출
		}

	}

	@Override
	public MovieVO getOneMovies(String movieId) {
		return movieDao.selectOneMovie(movieId);
	}

	@Override
	public boolean updateMovie(MovieVO movieVO) {
		return movieDao.updateOneMovie(movieVO) > 0;
	}

	@Override
	public boolean removeOneMovie(String movieId) {
		return movieDao.deleteOneMovie(movieId) > 0;
	}

	@Override
	public boolean addLikeCount(String movieId) {
		return movieDao.addLikeCount(movieId) > 0;
	}

	@Override
	public boolean setLike(int likeCount, String movieId) {
		return movieDao.setLike(likeCount, movieId) > 0;
	}

}
