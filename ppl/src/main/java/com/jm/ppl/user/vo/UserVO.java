package com.jm.ppl.user.vo;

import com.jm.ppl.authorization.vo.AuthorizationVO;

public class UserVO {
	
	private String userId;
	private String userName;
	private String userPassword;
	private String authorizationId;
	private String registDate;
	private boolean isLike = false;
	
	private AuthorizationVO authorization;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public AuthorizationVO getAuthorization() {
		if(authorization == null){
			authorization = new AuthorizationVO();
		}
		return authorization;
	}

	public void setAuthorization(AuthorizationVO authorization) {
		this.authorization = authorization;
	}

	public boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(boolean isLike) {
		this.isLike = isLike;
	}
	

}