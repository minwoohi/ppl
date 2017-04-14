package com.jm.ppl.user.service;

import com.jm.ppl.user.vo.UserVO;

public interface UserService {
	
	public boolean signUpUser(UserVO newUser);
	
	public UserVO signInUser(UserVO user);
	
	public boolean isDuplicatedUserId(String userId);
}
