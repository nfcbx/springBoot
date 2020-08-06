package toolTest.http;

import com.google.common.collect.Maps;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.joda.time.LocalDateTime;
import 多线程.自旋锁.LockTest4;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

public class HttpHandlerTest1 implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("请求开始" + LocalDateTime.now().toString());
        HashMap<String, Object> paramMap = Maps.newHashMap();
        if (httpExchange.getRequestMethod().equals("GET")) {
            //GET请求读queryString
            String paramStr = httpExchange.getRequestURI().getQuery();
            System.out.println(paramStr);
            String[] split = paramStr.split("&");
            for (String str : split) {
                String[] split1 = str.split("=");
                if (split1 != null && split1.length == 2) {
                    paramMap.put(split1[0], split1[1]);
                }
            }

        }

        //生成html
        StringBuilder responseContent = new StringBuilder();
        String uuid = UUID.randomUUID().toString();

        uuid = uuid.replace("-", "");

        uuid = paramMap.get("name").toString();
        responseContent.append("测试页面" + uuid);

        new LockTest4().demo("aaa-" + uuid);

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
