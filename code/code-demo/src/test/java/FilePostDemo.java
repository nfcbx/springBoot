import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by ZSX on 2018/12/5.
 *
 * @author ZSX
 */
public class FilePostDemo {

    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("https://tool-compress.soogif.com/zip/fileUpload");

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(200000)
                    .setSocketTimeout(200000)
                    .build();
            httppost.setConfig(requestConfig);

            FileBody bin = new FileBody(new File("C:\\Users\\QDHL\\Downloads\\43a39d58gy1fjs7x4rbusg208c071x6t.gif"));
            StringBody comment = new StringBody("006KWXKpgy1fmc8ththssg30920bre81.gif", ContentType.TEXT_PLAIN);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("file", bin)
                    .addPart("filename", comment)
                    .addPart("type", new StringBody("S_2M", ContentType.TEXT_PLAIN))
                    .addPart("imageUrl", new StringBody("", ContentType.TEXT_PLAIN))
                    .build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    System.out.println(responseEntityStr);
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
