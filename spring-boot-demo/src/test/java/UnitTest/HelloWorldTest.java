package UnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.zsx.App;
import com.zsx.service.TestService;
import com.zsx.service.impl.TestServiceImpl;

/**
 * springBoot 1.4版本后的单元测试
 * @author developer
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = App.class)
public class HelloWorldTest {
	
	@Autowired
	private TestService testService;

	@Test
	public void name() {
		
		
		
//		testService = new TestServiceImpl();
		
		testService.gettime();
		
		
		
	}

}


/**

异常一：

java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test

解决方法： 添加启动类即可 classes = App.class
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = App.class)



*/