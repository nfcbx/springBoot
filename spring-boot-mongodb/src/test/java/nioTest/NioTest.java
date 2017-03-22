package nioTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class NioTest {

	public static void main(String[] args) {
		FileChannel channel = null;
		try {
			File file = new File("C:/zhao.py");
			FileInputStream fis = new FileInputStream(file);
			
			channel = fis.getChannel();
			
			long position = channel.position();
			System.out.println(position);
			
			long size = channel.size();
			System.out.println(size);
			
			
			ByteBuffer buffer = ByteBuffer.allocate(1);
			
			int capacity = buffer.capacity();
			int limit = buffer.limit();
			System.out.println(capacity + " --- " + limit);

			{
				int position2 = buffer.position();
				System.out.println(position2);
			}
			
			
			channel.read(buffer);

			
//			byte b = buffer.get();
//			System.out.println(b);
			{
				int position2 = buffer.position();
				System.out.println(position2);
				
				buffer.flip();
				
				byte b = buffer.get();
				System.out.println(b);
				
			}
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
