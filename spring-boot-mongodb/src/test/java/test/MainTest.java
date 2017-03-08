package test;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import com.alibaba.fastjson.JSON;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MainTest {

	public static void main(String[] args) {
		
		MongoClientOptions.Builder build = new MongoClientOptions.Builder();  
        //与数据最大连接数50  
        build.connectionsPerHost(50);  
        //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待  
        build.threadsAllowedToBlockForConnectionMultiplier(50);  
        build.connectTimeout(1*60*1000);  
        build.maxWaitTime(2*60*1000);  
        MongoClientOptions options = build.build();  
        MongoClient client = new MongoClient("127.0.0.1", options); 
        //获取数据库test,不存在的话，会自动建立该数据库  
        MongoDatabase db = client.getDatabase("test");
        //获取data集合，不存在的话，会自动建立该集合（相当于关系数据库中的数据表）  
        MongoCollection<Document> users = db.getCollection("foo"); 
       //用文档操作
        Document document = new Document();  
        document.append("firstName", "lei2");  
        document.append("address", "sichuan chengdu");  
        
//        Person p = new Person();
//        p.setId("11111111111");z
//        p.setName("张三");
//        p.setSchool("南开大学");
//        p.setAge(100);
//        Document document =  Document.parse(JSON.toJSONString(p)); 
        users.insertOne(document);  
        //MongoClient使用完后必须要close释放资源  
        
        
//        FindIterable<Document> find = users.find();
//        
//        users.find(new Bson() {
//			
//			public <TDocument> BsonDocument toBsonDocument(Class<TDocument> arg0, CodecRegistry arg1) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
//        
//        MongoCursor<Document> iterator = find.iterator();
//        
//        if (iterator.hasNext()) {
//			Document document2 = iterator.next();
//			System.out.println(JSON.toJSONString(document2));
//		}
        
        
        client.close();  
		
		
		
	}

}
