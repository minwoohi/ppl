package com.jm.ppl.admin.authorization.biz;

import java.util.List;

import com.jm.ppl.admin.authorization.vo.AuthorizationSearchVO;
import com.jm.ppl.admin.authorization.vo.AuthorizationVO;


public interface AuthorBiz {

	public boolean inputNewAuthor(AuthorizationVO authorizationVO);
	
	public List<AuthorizationVO> selectAllUser(AuthorizationSearchVO authorizationSearchVO);
	
	public boolean updateAuthorizationInfo(AuthorizationVO authorizationVO);

	public boolean deleteOneAuthorization(String authorizationId);
}
