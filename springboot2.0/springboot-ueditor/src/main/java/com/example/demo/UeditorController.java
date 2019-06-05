package com.example.demo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by highness on 2019/5/12 0012.
 */

@Controller
public class UeditorController {





    @Value("${image.temp.path}")
    private String imageTempPath;


    @Value("${image.show.path}")
    private String imageShowPath;

    /**
     * 修改获取config.json的方式
     * 也需要修改 ueditor.config.js 里面的 serverUrl
     * // , serverUrl: URL + "jsp/controller.jsp"
     * , serverUrl: "/config"
     *
     * @param action
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/config")
    public String config(String action, HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/js/ueditor-1.4.3.3/jsp/config.json";
    }

    @RequestMapping("/uploadimage")
    @ResponseBody
    public String uploadImage(HttpServletRequest request) {
//        Gson gson = new Gson();
        JSONObject image = new JSONObject();

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("upfile");
        System.out.println("upfile 文件个数为： " + files.size());
        try {
            MultipartFile file = files.get(0);
            System.out.println();
            String fileName = file.getOriginalFilename();
            System.out.println("接收到上传数据，图片名称为 ：" + fileName);

            System.out.println(request.getServletPath());
            System.out.println(request.getContextPath());

            String fileNameSuffix = fileName.substring(fileName.lastIndexOf("."));
//          统一为小写
            fileNameSuffix = fileNameSuffix.toLowerCase();
            String uuid = UUID.randomUUID().toString();
            String tempFileName = uuid + fileNameSuffix;

            String tempFile = imageTempPath + "/" + tempFileName;
            String showFile = imageShowPath + "/" + tempFileName;

            System.out.println(tempFile);
            System.out.println(showFile);

            file.transferTo(new File(tempFile));
//            file.transferTo(new File(showFile));

            String path = "http://localhost:88/images/" + tempFileName; // fastdfsClient.uploadFile(files.get(0), "", false);
//            System.out.println("图片上传成功,上传路径为 ：" + serverPath + path);

//            image.setUrl(serverPath + path);
            image.put("url", path);
            image.put("state", "SUCCESS");
            image.put("original", fileName);
            image.put("title", fileName);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("图片上传失败，失败原因:" + e.getMessage());
            image.put("state", "FAIL");
        }
//        com.baidu.ueditor.ActionEnter;
        return image.toString();
    }


}
