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
import com.jm.ppl.common.web.pager.ClassicPageExplorer;
import com.jm.ppl.common.web.pager.PageExplorer;
import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;
import com.jm.ppl.movie.biz.MovieBiz;
import com.jm.ppl.movie.biz.MovieBizImpl;
import com.jm.ppl.movie.vo.MovieSearchVO;
import com.jm.ppl.movie.vo.MovieVO;
import com.jm.ppl.user.vo.UserVO;

public class ViewMovieListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieBiz movieBiz;

	public ViewMovieListServlet() {
		movieBiz = new MovieBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 세션 확인
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("_USER_");
		
		String pageNo = request.getParameter("pageNo");
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		MovieSearchVO movieSearchVO = new MovieSearchVO();
		movieSearchVO.setPager(pager);
		List<MovieVO> movieList = movieBiz.getAllMovies(movieSearchVO);
		
		PageExplorer pagerEx = new ClassicPageExplorer(pager);
		
		String page = pagerEx.getPagingList("pageNo", "[@]", "prev", "Next", "searchForm");
		
		request.setAttribute("movieList", movieList);
		request.setAttribute("movieCount", pager.getTotalArticleCount());
		request.setAttribute("pager", page);
		
		
		if(userVO == null){
			request.setAttribute("isNormalUser",false);
			request.setAttribute("isAdminUser", false);
			request.setAttribute("isOperatorUser", false);
			request.getRequestDispatcher("/user/signIn").forward(request, response);
		}
		else{
		request.setAttribute("isNormalUser", userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER));
		request.setAttribute("isOperatorUser", userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
		request.setAttribute("isAdminUser", userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER));
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/movie/list.jsp");
		dispatcher.forward(request, response);
	}

}
