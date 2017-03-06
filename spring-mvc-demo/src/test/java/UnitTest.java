import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/resources/applicationContext.xml", 
		"file:src/main/resources/spring-hibernate.xml", 
		"file:src/main/resources/springMVC-servlet.xml" })
public class UnitTest {
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Test
	public void name() {
		Session session = sessionFactory.getCurrentSession() == null ? sessionFactory  
                .openSession() : sessionFactory.getCurrentSession();
		
        session.createSQLQuery("select now()");
		
		
	}

}
