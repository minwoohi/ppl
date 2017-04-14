package com.jm.ppl.items.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.MultipartHttpServletRequest;
import com.jm.ppl.common.web.MultipartHttpServletRequest.MultipartFile;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.user.vo.UserVO;

public class DoItemModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ItemsService itemsService;   
    public DoItemModifyActionServlet() {
    	itemsService = new ItemsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {
			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
		
			String itemId = request.getParameter("itemId");
			String itemName = multipart.getParameter("itemName");
			String itemBrand = multipart.getParameter("itemBrand");
			String itemProductCode = multipart.getParameter("itemProductCode");
			String itemPriceString = multipart.getParameter("itemPrice");
			int itemPrice = 0;
			//System.out.println(itemPriceString.length());
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
			if( post != null && post.getFileSize() > 0 ) {
				postFileName = post.getFileName();
				
				File dir = new File("D:\\items\\post\\" + itemName );
				dir.mkdirs();
				post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
			}
			
			ItemsVO itemsVO = new ItemsVO();
			
			itemsVO.setItemId(itemId);
			itemsVO.setItemBrand(itemBrand);
			itemsVO.setItemName(itemName);
			itemsVO.setItemPost(postFileName);
			itemsVO.setItemPrice(itemPrice);
			itemsVO.setItemProductCode(itemProductCode);
			
			if( itemsService.modifyItem(itemsVO) ) {
				StringBuffer script = new StringBuffer();
				script.append("<script type='text/javascript'>");
				script.append("		opener.location.reload();");
				script.append(" 	self.close();");
				script.append("</script>");
				
				PrintWriter writer = response.getWriter();
				writer.write(script.toString());
				writer.flush();
				writer.close();
			}
			else {
				response.sendRedirect("/ppl/item/modify?itemId=" + itemId);
			}
		}
		else {
			response.sendError(404);
		}
	}

}
