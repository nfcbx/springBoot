package toolTest.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class HttpHandlerTest1 implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("请求开始" + LocalDateTime.now().toString());
        //生成html
        StringBuilder responseContent = new StringBuilder();
        responseContent.append("测试页面" + UUID.randomUUID().toString());

        String responseContentStr = responseContent.toString();
        byte[] responseContentByte = responseContentStr.getBytes("utf-8");

        //设置响应头，必须在sendResponseHeaders方法之前设置！
//        httpExchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");
//        httpExchange.getResponseHeaders().add("Content-Type:", "application/json; charset=utf-8");

        //设置响应码和响应体长度，必须在getResponseBody方法之前调用！
        httpExchange.sendResponseHeaders(200, responseContentByte.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();

    }
}
