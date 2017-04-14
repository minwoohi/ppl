package com.jm.ppl.movie.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jm.ppl.movie.service.MovieService;
import com.jm.ppl.movie.service.MovieServiceImpl;
import com.jm.ppl.movie.vo.MovieVO;
import com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException;

public class MovieLikeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService movieService;

	public MovieLikeCountServlet() {
		movieService = new MovieServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String movieId = request.getParameter("movieId");
		
		MovieVO movieVO = movieService.getOneMovies(movieId);
		movieVO.setMovieLikeCount(movieVO.getMovieLikeCount() + 1);
		
		System.out.println(movieVO);
		
		boolean isSuccess = movieService.updateMovie(movieVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", isSuccess ? "success" : "fail");
		map.put("likeCount", movieVO.getMovieLikeCount());
		
		System.out.println(map);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
