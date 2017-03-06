package WebServiceTest;

import java.util.Calendar;

import javax.jws.WebService;

@WebService(name = "testService" , targetNamespace = "http://ws.itcast.cn/")
public class TTT {
	
	public String name() {
		return Calendar.getInstance().getTime().toLocaleString();
	}
}
