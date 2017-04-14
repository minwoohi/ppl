package com.jm.ppl.movie.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.MultipartHttpServletRequest;
import com.jm.ppl.common.web.MultipartHttpServletRequest.MultipartFile;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.movie.service.MovieService;
import com.jm.ppl.movie.service.MovieServiceImpl;
import com.jm.ppl.movie.vo.MovieVO;
import com.jm.ppl.user.vo.UserVO;

public class MovieModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MovieService movieService;

	public MovieModifyServlet() {
		movieService = new MovieServiceImpl();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");
		

		if (userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {
			

			String movieId = request.getParameter("movieId");
			System.out.println("수정: "+movieId);

			MovieVO movieVO = movieService.getOneMovies(movieId);

			request.setAttribute("movieVO", movieVO);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/movie/modify.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {

			// web에서 데이터 가져오기 - MultipartHttpServletRequest 이용.
			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);

			String movieId = request.getParameter("movieId");
			String movieTitle = multipart.getParameter("movieTitle");
			String movieSubtitle = multipart.getParameter("movieSubtitle");
			String movieDirector = multipart.getParameter("movieDirector");
			String movieActor = multipart.getParameter("movieActor");
			String movieIntro = multipart.getParameter("movieIntro");
			String movieGenre = multipart.getParameter("movieGenre");

			MultipartFile moviePost = multipart.getFile("moviePost");

			MovieVO movieVO = new MovieVO();

			if (moviePost != null && moviePost.getFileSize() > 0) {

				String path = "C:\\Users\\Admin\\Documents\\workspace-sts-3.8.3.RELEASE\\ppl\\src\\main\\webapp\\static\\img\\";

				path += movieTitle;
				File dir = new File(path);

				dir.mkdirs();

				moviePost.write(path + File.separator + moviePost.getFileName());
				movieVO.setMoviePost(moviePost.getFileName());

			} else {

				MovieVO movieVO1 = movieService.getOneMovies(movieId);
				movieVO.setMoviePost(movieVO1.getMoviePost());
			}

			movieVO.setMovieId(movieId);
			movieVO.setMovieTitle(movieTitle);
			movieVO.setMovieSubtitle(movieSubtitle);
			movieVO.setMovieDirector(movieDirector);
			movieVO.setMovieActor(movieActor);
			movieVO.setMovieIntro(movieIntro);
			movieVO.setMovieGenre(movieGenre);
			movieVO.setMovieLikeCount(0);

			boolean isSuccess = movieService.updateMovie(movieVO);

			if (isSuccess) {

				/*
				 * 새창을 닫아주는 메서드. PrintWriter writer = response.getWriter();
				 * StringBuffer sb = new StringBuffer();
				 * sb.append(" <script type='text/javascript'> ");
				 * sb.append(" opener.location.reload(); ");
				 * sb.append(" self.close(); "); sb.append(" </script> ");
				 * 
				 * writer.write(sb.toString()); writer.flush(); writer.close();
				 */

				response.sendRedirect("/ppl/movie/detail?movieId=" + movieId);
				System.out.println("수정 완료");

			} else {
				response.sendRedirect("/ppl/movie/modify?movieId=" + movieId);
				System.out.println("수정 오류");
			}

		} else {
			response.sendError(404);
		}

	}

}
