package test;

import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zsx.App;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = App.class)
public class UnitTest {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void name() {
		System.out.println(mongoTemplate);
//		DBCollection collection = mongoTemplate.getCollection("foo");
//		
//		System.out.println(collection.getCount());
		
//		DBObject query = new BasicDBObject();
//		query.put("_id",false);
//		query.put("address", "&gt");
//		
//		DBCursor find = collection.find(new BasicDBObject());
//		
//		System.out.println(find.size());
//		Iterator<DBObject> iterator = find.iterator();
//		if (iterator.hasNext()) {
//			DBObject next = iterator.next();
//			System.out.println(next);
//		}
//		
		
		Criteria criteria = new Criteria();
		
//		criteria.where("hello").regex(".*?\\" + "" + ".*");
		
		
		List<Object> find2 = mongoTemplate.find(new Query(criteria), Object.class, "data");
//		List<Object> find2 = mongoTemplate.find(new Query(criteria), Object.class); // 报错
		
		System.out.println(find2.size());
		
		for (Object object : find2) {
			System.out.println(object);
		}
		
		
//		Document document = new Document();  
//        document.append("firstName", "lei222");  
//        document.append("address", "sichuan chengdu22"); 
//		
//		mongoTemplate.insert(document, "foo");
		
	}

}
