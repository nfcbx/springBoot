import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/resources/applicationContext.xml", 
		"file:src/main/resources/spring-hibernate.xml", 
		"file:src/main/resources/springMVC-servlet.xml" })
public class UnitTest2 {
	
	
	@Test
	public void name() {
		
		
	}

}
