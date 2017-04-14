package com.jm.ppl.movie.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.MultipartHttpServletRequest;
import com.jm.ppl.movie.service.MovieService;
import com.jm.ppl.movie.service.MovieServiceImpl;
import com.jm.ppl.user.vo.UserVO;

public class MovieDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MovieService movieService;

	public MovieDeleteServlet() {
		movieService = new MovieServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String movieId = request.getParameter("movieId");
//		
//		request.setAttribute("movieId", movieId);

		response.sendError(404);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {

			String movieId = request.getParameter("movieId");
			

			if (movieService.removeOneMovie(movieId)) {
				response.sendRedirect("/ppl/movie/list");
			} else {
				response.sendError(404);
			}

		} else {
			response.sendError(404);
		}

	}

}
