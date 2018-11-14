package kr.or.ddit.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
// controller의 경우 service 스프링 빈을 주입받기때문에
// service, dao에 대한 설정파일도 필요하다.
@ContextConfiguration(locations= {"classpath:kr/or/ddit/config/spring/servlet-context.xml",
									"classpath:kr/or/ddit/config/spring/root-context.xml",
									"classpath:kr/or/ddit/config/spring/datasource-context.xml"})

// 스프링 컨테이너를 웹 기반에서 활용가능한 WebApplicationContext로 생성
@WebAppConfiguration
public class ControllerTestConfig {
	@Resource(name="datasource")
	private DataSource datasource;
	
	// webApplicationContext ==> mockMvc(dispatcherServlet) 생성을 위해 필요하다.
	
	@Autowired
	protected WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		/*ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("kr/or/ddit/config/db/dbInit.sql"));
		populator.setContinueOnError(false);
		DatabasePopulatorUtils.execute(populator,datasource);*/
	}
	
	@Ignore   // 불필요한 파일을 skip
	@Test
	public void test() {
		assertFalse(!true);
	
	}
}


























