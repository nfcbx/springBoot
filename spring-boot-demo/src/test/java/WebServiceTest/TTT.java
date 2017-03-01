package WebServiceTest;

import java.util.Calendar;

import javax.jws.WebService;

@WebService
public class TTT {
	
	public String name() {
		return Calendar.getInstance().getTime().toLocaleString();
	}
}
