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
public class UnitTest2 {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void name() {
		System.out.println(mongoTemplate.getCollectionNames());
	}

}

/**
 *  第一次登录不要启动授权，连接数据库后，切换到admin数据库
 *  > use admin
 *  
 *  添加一个用户
 *  > db.addUse("zhao", "shuxue") 这种方式太老了   -- 用户名zhao， 密码为shuxue
 *  
 *  > db.createUser({user: "root", pwd: "123456", roles: ["root"] })
 *  
 *  推荐创建用户方式：最好指定角色类型以及数据库
 *  ***注意：：：：** 为其他数据库添加用户，添加用户前需要切换到该数据库** 
 *  > db.createUser({user: "root", pwd: "123456", roles: [{role: "readWrite", db: "mydb"}] })
 *  
 *  
 *  db.createUser(
		 {
		   user: "client",
		   pwd: "111111",
		   roles: [
		      { role: "readWrite", db: "db_report" }
		   ]
		 }
		)
 *  
 *  
 *  给用户授权
 *  > db.auth("root", "123456")
 *  
 *  删除用户
 *  > db.dropUser("test");
 * 
 *  然后退出使用认证方式启动MongoDB
 *  
 *  > mongod --auth --dbpath=C:\MongoDB\db --httpinterface
 *  
 *  // --httpinterface 加上该参数，可以在浏览器输入127.0.0.1:28017 访问
 *  
 *  // --logpath=C:\MongoDB\logs\mongodb.log
 *  
 *  
 *  登录命令
 *  > ./mongo 127.0.0.1:27000/admin  -uzhao -pshuxue
 *  也可以不指定数据库
 * 
 * Built-In Roles（内置角色）：
1. 数据库用户角色：read、readWrite;
2. 数据库管理角色：dbAdmin、dbOwner、userAdmin；
3. 集群管理角色：clusterAdmin、clusterManager、clusterMonitor、hostManager；
4. 备份恢复角色：backup、restore；
5. 所有数据库角色：readAnyDatabase、readWriteAnyDatabase，userAdminAnyDatabase、dbAdminAnyDatabase
6. 超级用户角色：root  
7. 内部角色：__system
 *
 * 
 * 
 * 
 * 
 * */
