package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVo;

public interface UserServiceInf {
	
	/**
	 * Method : selectUser
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param userid
	 * @return
	 * Method 설명 : 로그인시 필요 
	 */
	UserVo selectUser(String userid);
	
	
}