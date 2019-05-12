
# 复制jsp到static目录下

- 把index.html内容复制到webapps下的jsp文件里
- jsp目录下的lib文件下有5个jar包，其中4个可以用maven引入
```
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>1.10</version>
</dependency>

<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.2</version>
</dependency>

<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.4</version>
</dependency>

<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20140107</version>
</dependency>
```        

还剩一个ueditor-1.1.2.jar，把它复制到 webapp/WEB-INF/lib/ueditor-1.1.2.jar。

## 修改ueditor.config.js配置

### 修改serverUrl

- serverUrl 为 `服务器统一请求接口路径`，默认为：URL + "jsp/controller.jsp"
修改为："/config"

+ 新建一个controller文件，里面添加如下的action

```
@RequestMapping("/config")
public String config() {
    return "redirect:/js/ueditor-1.4.3.3/jsp/config.json";
}
```
这样就可以把自定义url去请求config配置了。

- 修改上传图片的url

在config.json里面可以看到上传图片的url配置信息为：
```
"imageActionName": "uploadimage", /* 执行上传图片的action名称 */
```

在页面js代码里添加下面一段代码，
```
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');

UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
        return 'http://localhost:9090/uploadimage'; //在这里返回我们实际的上传图片地址
    } else {
        return this._bkGetActionUrl.call(this, action);
    }
}
```

改为我们自定义的图片上传url —— `'http://localhost:9090/uploadimage';`

+ 后台添加上传图片的接口

```
@RequestMapping("/uploadimage")
@ResponseBody
public String uploadImage(HttpServletRequest request) {
    JSONObject image = new JSONObject();
    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("upfile");
    try {
        MultipartFile file = files.get(0);
        String fileName = file.getOriginalFilename();
        System.out.println("接收到上传数据，图片名称为 ：" + fileName);

        String fileNameSuffix = fileName.substring(fileName.lastIndexOf("."));
//          统一为小写
        fileNameSuffix = fileNameSuffix.toLowerCase();
        String uuid = UUID.randomUUID().toString();
        String tempFileName = uuid + fileNameSuffix;

        String tempFile = imageTempPath + "/" + tempFileName;

        file.transferTo(new File(tempFile));

        String path = "http://localhost:8888/images/" + tempFileName; 

        image.put("url", path);
        image.put("state", "SUCCESS");
        image.put("original", fileName);
        image.put("title", fileName);

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("图片上传失败，失败原因:" + e.getMessage());
        image.put("state", "FAIL");
    }
    return image.toString();
}
```    
> - 接口返回的字符串中有四个属性：state、url、original、title
> - 取文件 upfile 对应config.json里面的配置：`"imageFieldName": "upfile", /* 提交的图片表单名称 */`
> - 把文件放到一个固定的文件夹下，
>    ```
>    String tempFile = imageTempPath + "/" + tempFileName;
>    file.transferTo(new File(tempFile));
>    ```
> - 使用nginx做静态图片服务器， `String path = "http://localhost:8888/images/" + tempFileName; `

## 修改上传文件的大小限制

### 后端

修改application.properties
```
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB
```
### 前端
修改config.json，默认是2048000，默认是2MB，这里改为了200MB。
```
"imageMaxSize": 204800000, /* 上传大小限制，单位B */
```


## 配置Nginx

修改nginx.conf文件，添加一个server，图片路径对应上边的 `http://localhost:8888/images/`

```
server {
    listen       8888;
    server_name  localhost;

    location /images/ {
        alias  D:/springBoot/springboot2.0/springboot-ueditor/images/;
    }
}
```    

# end

---
