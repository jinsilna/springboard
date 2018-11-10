package kr.or.ddit.user.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;
@Repository
public class UserDao implements UserDaoInf{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	public UserVo selectUser(String userId){
		return sqlSessionTemplate.selectOne("user.selectUser",userId);

	}
}
