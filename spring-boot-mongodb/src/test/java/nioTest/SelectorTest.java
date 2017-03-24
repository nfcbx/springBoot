package nioTest;

import java.io.IOException;
import java.nio.channels.Selector;

public class SelectorTest {

	public static void main(String[] args) {
		Selector selector = null;
		try {
			selector = Selector.open();
			
//			while(true){
//				int select = selector.select();
//				System.out.println(select);
//				
//			}
			
			int i=0;
			
			for(;;){
				System.out.println(i++);
				if (i > 100) {
					break;
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (selector != null) {
				try {
					selector.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
