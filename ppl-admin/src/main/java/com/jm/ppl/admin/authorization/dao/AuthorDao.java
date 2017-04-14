package com.jm.ppl.admin.authorization.dao;

import java.util.List;

import com.jm.ppl.admin.authorization.vo.AuthorizationSearchVO;
import com.jm.ppl.admin.authorization.vo.AuthorizationVO;

public interface AuthorDao {

	public String generateNewAuthorizationId();
	
	public int insertNewAuthorization(AuthorizationVO authorizationVO);
	
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO);
	
	public List<AuthorizationVO> selectAllauthorization(AuthorizationSearchVO authorizationSearchVO);
	
	public int updateAuthorization(AuthorizationVO authorizationVO);
	
	public int deleteOneAuthorization(String authorizationId);
	
}
