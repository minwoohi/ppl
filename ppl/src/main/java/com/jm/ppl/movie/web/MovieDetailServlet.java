package com.jm.ppl.movie.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.MultipartHttpServletRequest;
import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.movie.service.MovieService;
import com.jm.ppl.movie.service.MovieServiceImpl;
import com.jm.ppl.movie.vo.MovieVO;
import com.jm.ppl.user.vo.UserVO;

public class MovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MovieService movieService;
	
	private	 ItemsService itemsService;
	//private ItemsSearchVO itemSearchVO;
	private	 ItemsVO itemsVO;
	

	public MovieDetailServlet() {
		movieService = new MovieServiceImpl();
		itemsService = new ItemsServiceImpl();
		//itemSearchVO = new ItemsSearchVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String movieId = request.getParameter("movieId");
		String pageNo = request.getParameter("pageNo");

		System.out.println("디테일 서블릿 : " + movieId);
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");
		
		MovieVO movie = movieService.getOneMovies(movieId);
		
		System.out.println("디테일 겟원무비 : " + movie);
		
		// Pager pager = PagerFactory.getPager(Pager.ORACLE);
		// pager.setPageNumber(pageNo);
		ItemsSearchVO itemSearchVO = new ItemsSearchVO();
		itemSearchVO.setMovieId(movieId);
		itemSearchVO.getPager().setPageNumber(pageNo);
		
		System.out.println(movieId);
		System.out.println(itemSearchVO);
		
		List<ItemsVO> itemsList = itemsService.getAllitemsByIds(itemSearchVO);
		
		System.out.println(itemsList);
		
		request.setAttribute("itemsList", itemsList);
		request.setAttribute("movie", movie);
		
		request.setAttribute("isNormalUser", userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER));
		request.setAttribute("isOperatorUser", userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
		request.setAttribute("isAdminUser", userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/movie/detail.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

}
