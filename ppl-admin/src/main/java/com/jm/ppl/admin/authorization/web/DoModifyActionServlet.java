package com.jm.ppl.admin.authorization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jm.ppl.admin.authorization.service.AuthorService;
import com.jm.ppl.admin.authorization.service.AuthorServiceImpl;
import com.jm.ppl.admin.authorization.vo.AuthorizationVO;

/**
 * Servlet implementation class DoModifyActionServlet
 */
public class DoModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private AuthorService service;
    
    public DoModifyActionServlet() {
    	service = new AuthorServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String authorizationId = request.getParameter("authorizationId");
		String authorizationName = request.getParameter("authorizationName");
		String parentAuthorizationId = request.getParameter("parentAuthorizationId");
		
		AuthorizationVO authorizationVO = new AuthorizationVO();
		authorizationVO.setAuthorizationId(authorizationId);
		authorizationVO.setAuthorizationName(authorizationName);
		authorizationVO.setParentAuthorizationId(parentAuthorizationId);
		
		boolean isSuccess = service.updateAuthorizationInfo(authorizationVO);
		
		if(isSuccess){
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("status","success");
			
		    //편하게 json만들어줌
			Gson gson =new Gson();
			String json = gson.toJson(map);
			
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		}
	
	}
}
