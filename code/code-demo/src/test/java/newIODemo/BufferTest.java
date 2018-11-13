package newIODemo;

import java.nio.IntBuffer;

/**
 * Created by ZSX on 2018/11/13.
 *
 * @author ZSX
 */
public class BufferTest {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }



    }

}
