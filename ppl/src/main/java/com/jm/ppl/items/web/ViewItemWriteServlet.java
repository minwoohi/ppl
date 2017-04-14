package com.jm.ppl.items.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.actor.service.ActorService;
import com.jm.ppl.actor.service.ActorServiceImpl;
import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.MultipartHttpServletRequest;
import com.jm.ppl.common.web.MultipartHttpServletRequest.MultipartFile;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.user.vo.UserVO;

public class ViewItemWriteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ItemsService itemsService;
	private ActorService actorService;
	
    public ViewItemWriteServlet() {
    	itemsService = new ItemsServiceImpl();
    	actorService = new ActorServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/item/write.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {
			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
	
			String actorId = multipart.getParameter("actorId");
			String movieId = multipart.getParameter("movieId");
			String dramaId = multipart.getParameter("dramaId");
			
			System.out.println("dramaID : " + dramaId);
			
			String itemName = multipart.getParameter("itemName");
			String itemBrand = multipart.getParameter("itemBrand");
			String itemProductCode = multipart.getParameter("itemProductCode");
			String itemPriceString = multipart.getParameter("itemPrice");
			
			int itemPrice = 0;
			if( itemPriceString.length() == 0 ) {
				itemPriceString = "0";
			}
			try {
				itemPrice = Integer.parseInt(itemPriceString);
			}
			catch(NumberFormatException e) {
				throw new RuntimeException("존재하지 않는 게시글이거나 잘못된 접근입니다.");
			}
			String postFileName = "";
			
			MultipartFile post = multipart.getFile("post");
			if( post != null && post.getFileSize() > 0 ){
				
				postFileName = post.getFileName();
				
				File dir = new File("D:\\items\\post\\" + itemName);
				dir.mkdirs();
				post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
			}
					
			ItemsVO itemVO = new ItemsVO();
			
			itemVO.setActorId(actorId);
			itemVO.setDramaId(dramaId);
			itemVO.setMovieId(movieId);
			itemVO.setItemBrand(itemBrand);
			itemVO.setItemName(itemName);
			itemVO.setItemPost(postFileName);
			itemVO.setItemPrice(itemPrice);
			itemVO.setItemProductCode(itemProductCode);
					
			
			if ( itemsService.addItem(itemVO) ) {
				StringBuffer script = new StringBuffer();
				script.append("<script type='text/javascript'>");
				script.append("		opener.location.reload();");
				script.append(" 	self.close();");
				script.append("</script>");
				
				PrintWriter writer = response.getWriter();
				writer.write(script.toString());
				writer.flush();
				writer.close();
				System.out.println("애드 호출.");			
			}
			else if ( actorId != null ){
				System.out.println("배우 아이디는 있지만 추가안됨");
				response.sendRedirect("/ppl/actor/write?actorId=" + actorId);
			}
			else if ( dramaId != null ){
				System.out.println("드라마 아이디는 있지만 추가안됨");
				response.sendRedirect("/ppl/drama/write?dramaId=" + dramaId);
			}
			else if ( movieId != null ){
				System.out.println("무비 아이디는 있지만 추가안됨");
				response.sendRedirect("/ppl/movie/write?movieId=" + movieId);
			}
		}
		else {
			response.sendError(404);
		}
	}

}
