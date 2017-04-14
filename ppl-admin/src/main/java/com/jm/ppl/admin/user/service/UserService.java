package com.jm.ppl.admin.user.service;

import java.util.List;
import java.util.Map;

import com.jm.ppl.admin.user.vo.SignVO;
import com.jm.ppl.admin.user.vo.UserSearchVO;
import com.jm.ppl.admin.user.vo.UserVO;

public interface UserService {
	
public boolean inputNewUser(UserVO userVO);

public List<UserVO> selectAllUser(UserSearchVO userSearchVO);

public UserVO signInUser(UserVO user);

public UserVO selectOneUser(String userId);

public UserVO selectOneUser(UserVO userVO);

public boolean updateUserInfo(UserVO userVO);

public boolean deleteOneUser(String userId);

public Map<String,Object> getOneUserWithAuthorizations(String userId);

public boolean changeAthorizationId(String[] authorizationId);

public boolean updateAllAuthorization(String[] userArray, String toAuth, String fromAuth);

public SignVO viewSignUpUserByDate (int dateCont);

}
