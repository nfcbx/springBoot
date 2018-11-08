package newIODemo;

import java.io.IOException;
import java.nio.channels.Channel;

/**
 * Created by ZSX on 2018/11/7.
 *
 * @author ZSX
 */
public class NewIOTest1 {

    public static void main(String[] args) {


        Channel channel = new Channel() {
            @Override
            public boolean isOpen() {
                return false;
            }

            @Override
            public void close() throws IOException {

            }
        };


    }

}
