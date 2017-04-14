package com.jm.ppl.admin.user.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.admin.authorization.vo.AuthorizationVO;
import com.jm.ppl.admin.common.constants.AuthConst;
import com.jm.ppl.admin.user.service.UserService;
import com.jm.ppl.admin.user.service.UserServiceImpl;
import com.jm.ppl.admin.user.vo.UserVO;

/**
 * Servlet implementation class ViewDetailServlet
 */
public class ViewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	public ViewDetailServlet() {

		userService = new UserServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO useradmin = (UserVO) session.getAttribute("_USER_");

		if (useradmin == null) {
			response.sendRedirect("/ppl-admin/user/signIn");
		} else {
			if (useradmin.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {

				String userId = request.getParameter("userId");
				

				Map<String, Object> user = userService.getOneUserWithAuthorizations(userId);

				UserVO userVO = (UserVO) user.get("user");
				List<AuthorizationVO> authList = (List<AuthorizationVO>) user.get("authorizations");

				request.setAttribute("user", userVO);
				request.setAttribute("authList", authList);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/detail.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendError(404);
			}
		}

	}
}