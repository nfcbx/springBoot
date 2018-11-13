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
            System.out.println("position : " + intBuffer.position());
            System.out.println("limit : " + intBuffer.limit());
            System.out.println();
        }

        System.out.println("从写入模式切换到读取模式");
        intBuffer.flip();

        System.out.println("position : " + intBuffer.position());
        System.out.println("limit : " + intBuffer.limit());


        for (int i = 0; i < 5; i++) {
            int i1 = intBuffer.get();
            System.out.println("position : " + intBuffer.position());
            System.out.println("limit : " + intBuffer.limit());

            System.out.println(i1);
            System.out.println();
        }


    }

}
