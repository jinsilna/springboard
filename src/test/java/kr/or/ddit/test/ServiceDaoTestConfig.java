package kr.or.ddit.test;

import static org.junit.Assert.assertFalse;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


// service, dao에 대한 설정파일만 필요하다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:kr/or/ddit/config/spring/root-context.xml",
									"classpath:kr/or/ddit/config/spring/datasource-context.xml",
									"classpath:kr/or/ddit/config/spring/transaction-context.xml",
									"classpath:kr/or/ddit/config/spring/batch-context.xml"})
public class ServiceDaoTestConfig {
	@Resource(name="datasource")
	private DataSource datasource;
	
	@Before
	public void rangerSetup() {
		//  데이터 초기화
		//  delete query 호출 --> sql 스크립트를 실행해주는 녀석 
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("kr/or/ddit/config/db/dbInit.sql"));
		populator.setContinueOnError(false);
		DatabasePopulatorUtils.execute(populator,datasource);
	}
	
	@Ignore   // 불필요한 파일을 skip
	@Test
	public void test2222() {
		assertFalse(!true);
	
	}
}


























