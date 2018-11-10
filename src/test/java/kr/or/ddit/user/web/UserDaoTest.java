package kr.or.ddit.user.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.dao.UserDaoInf;
import kr.or.ddit.user.model.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:kr/or/ddit/config/spring/root-context.xml",
									"classpath:kr/or/ddit/config/spring/datasource-context.xml"})
public class UserDaoTest {

	@Resource(name="userDao")
	private UserDaoInf userDao;
	
	@Test
	public void UserDaoTest() {
		
		/***Given***/

		/***When***/
		UserVo userVo = userDao.selectUser("brown");

		/***Then***/
		assertNotNull(userVo);
		assertEquals("브라운", userVo.getName());
		assertEquals("brown", userVo.getUserId());
	}

}
