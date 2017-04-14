package com.jm.ppl.user.dao;

import com.jm.ppl.user.vo.UserVO;

public interface UserDao {
	
	public int insertNewUser(UserVO user);
	
	public UserVO selectOneUser(UserVO user);
	
	public int selectCountByUserId(String userId);
	
	
}
