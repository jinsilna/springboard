package kr.or.ddit.user.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;
@Repository
public class UserDao implements UserDaoInf{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * Method : selectUser
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 이름으로 값 가져오기 
	 */
	public UserVo selectUser(String userId){
		return sqlSessionTemplate.selectOne("user.selectUser",userId);

	}

	/**
	 * Method : insertUser
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 회원가입 insert
	 */
	@Override
	public int insertUser(UserVo userVo) {
		return sqlSessionTemplate.insert("user.insertUser",userVo);
	}
}
