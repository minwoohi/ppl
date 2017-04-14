package com.jm.ppl.movie.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.DownloadUtil;
import com.jm.ppl.movie.service.MovieService;
import com.jm.ppl.movie.service.MovieServiceImpl;
import com.jm.ppl.movie.vo.MovieVO;
import com.jm.ppl.user.vo.UserVO;

public class ViewMoviePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MovieService movieService;

	public ViewMoviePostServlet() {
		movieService = new MovieServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			String movieId = request.getParameter("movieId");

			MovieVO movie = movieService.getOneMovies(movieId);

			String postPath = "C:\\Users\\Admin\\Documents\\workspace-sts-3.8.3.RELEASE\\ppl\\src\\main\\webapp\\static\\img\\";

			postPath += movie.getMovieTitle();

			DownloadUtil downloadUtil = DownloadUtil.getInstance(postPath);
			downloadUtil.download(request, response, movie.getMoviePost(), movie.getMoviePost());

	}

}
