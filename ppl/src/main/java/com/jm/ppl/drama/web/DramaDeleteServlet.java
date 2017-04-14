package com.jm.ppl.drama.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.drama.service.DramaService;
import com.jm.ppl.drama.service.DramaServiceImpl;
import com.jm.ppl.user.vo.UserVO;

public class DramaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DramaService dramaService;

	public DramaDeleteServlet() {
		dramaService = new DramaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");

		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {

			String dramaId = request.getParameter("dramaId");

			if (dramaService.removeOneDrama(dramaId)) { // 드라마 삭제 성공시
				response.sendRedirect("/ppl/drama/list");
			} else {
				response.sendError(404);
			}
		} else {
			response.sendError(404);
		}
	}

}
