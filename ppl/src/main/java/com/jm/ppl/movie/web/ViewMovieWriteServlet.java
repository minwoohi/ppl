package com.jm.ppl.movie.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.MultipartHttpServletRequest;
import com.jm.ppl.common.web.MultipartHttpServletRequest.MultipartFile;
import com.jm.ppl.movie.biz.MovieBiz;
import com.jm.ppl.movie.biz.MovieBizImpl;
import com.jm.ppl.movie.vo.MovieVO;
import com.jm.ppl.user.vo.UserVO;

public class ViewMovieWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MovieBiz movieBiz;

	public ViewMovieWriteServlet() {
		movieBiz = new MovieBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER) || userVO.getAuthorizationId().equals("_USER_")) {
			response.sendError(404); // 가장 현명한 방법 (해커가 예측못하게)

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/movie/write.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER) 
				|| userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {

			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);

			String movieTitle = multipart.getParameter("movieTitle");
			String movieSubtitle = multipart.getParameter("movieSubtitle");
			String movieDirector = multipart.getParameter("movieDirector");
			String movieActor = multipart.getParameter("movieActor");
			String movieIntro = multipart.getParameter("movieIntro");
			String movieGenre = multipart.getParameter("movieGenre");

			String moviePostFileName = "";
			String movieDownloadUrl = "";

			MultipartFile post = multipart.getFile("moviePost");
			
			if (post != null && post.getFileSize() > 0) {

				moviePostFileName = post.getFileName();
				movieDownloadUrl = post.getFileName();

				File dir = new File(
						"C:\\Users\\Admin\\Documents\\workspace-sts-3.8.3.RELEASE\\ppl\\src\\main\\webapp\\static\\img\\"
								+ movieTitle);
				dir.mkdirs();

				post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
			}

			MovieVO movieVO = new MovieVO();
			movieVO.setMovieTitle(movieTitle);
			movieVO.setMovieSubtitle(movieSubtitle);
			movieVO.setMovieDirector(movieDirector);
			movieVO.setMovieActor(movieActor);
			movieVO.setMovieIntro(movieIntro);
			movieVO.setMoviePost(moviePostFileName);
			movieVO.setMovieGenre(movieGenre);

			// 글쓰기창 완료되면 창 닫기.
			if (movieBiz.addNewMovie(movieVO)) {
				StringBuffer script = new StringBuffer();
				script.append(" <script type='text/javascript'> ");
				script.append(" opener.location.reload(); ");
				script.append(" self.close(); ");
				script.append(" </script> ");

				PrintWriter writer = response.getWriter();
				writer.write(script.toString());
				writer.flush();
				writer.close();
			} else {
				response.sendError(404);
			}
		} else {
			response.sendError(404);
		}
	}
}
