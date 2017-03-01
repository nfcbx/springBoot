package WebServiceTest;

import javax.xml.ws.Endpoint;

public class WebServiceTest {
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8083/wcms/", new TTT());
	}

}
