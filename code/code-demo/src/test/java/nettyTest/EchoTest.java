package nettyTest;

import nettyTest.echoServer.EchoServer;

public class EchoTest {

    public static void main(String[] args) throws Exception {

        EchoServer echoServer = new EchoServer(58080);
        echoServer.start();

    }

}
