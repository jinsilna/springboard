package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVo;

public interface UserServiceInf {
	
	/**
	 * Method : selectUser
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param userid
	 * @return
	 * Method 설명 : 아이디 값으로 select
	 */
	UserVo selectUser(String userid);
	
	/**
	 * Method : insertUser
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 회원가입 insert 
	 */
	public int insertUser(UserVo userVo);
	
}