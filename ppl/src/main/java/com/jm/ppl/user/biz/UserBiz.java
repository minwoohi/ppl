package com.jm.ppl.user.biz;

import com.jm.ppl.user.vo.UserVO;

public interface UserBiz {
	public boolean signUpUser(UserVO newUser);
	
	public UserVO signInUser(UserVO user);
	
	public boolean isDuplicatedUserId(String userId);
}
