package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.UserVo;

public interface UserDaoInf {

	
	/**
	 * Method : selectUser
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param userid
	 * @return
	 * Method 설명 : Id로 값 가져오기 
	 */
	UserVo selectUser(String userid);
	
	/**
	 * Method : insertUser
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 회원가입을 위한 insert
	 */
	public int insertUser(UserVo userVo);
	
}