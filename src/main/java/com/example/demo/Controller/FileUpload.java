package com.example.demo.Controller;

import com.example.demo.utils.POIUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileUpload {
    @RequestMapping("/index")
    public String test() {
        return "index";
    }

    /**
     * @param file 要上传的文件
     * @return
     */

    @RequestMapping("fileUpload")
    @ResponseBody
    public String upload(@RequestParam("fileName") MultipartFile file[]) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(new File("E:/1.txt")));//构造一个BufferedReader类来读取文件
            int i = 0;
            for (MultipartFile f : file) {
                String imgName = null;
                System.out.println(i++);
                if (br.readLine() != null) {
                    imgName = br.readLine();
                }
                System.out.println(imgName);
                String str = f.getOriginalFilename();
                str = str.substring(str.indexOf("."));

                uploadFileUtil(f.getBytes(), "E:/img/", imgName + str);
            }

        } catch (Exception e) {
            return "失败";
        }

        return "成功";
    }

    @RequestMapping("isexcel")
    @ResponseBody
    public String excel(@RequestParam(value = "excel") MultipartFile file) {


        try {
            List<String[]> userList = POIUtil.readExcel(file);
            for (int i = 1; i < userList.size(); i++) {
                String[] users = userList.get(i);
                if (users != null) {
                    System.out.println(users.toString());

                    System.out.println(users[1]);
                    System.out.println(users[2]);
                    System.out.println(users[3]);
                    System.out.println(users[4]);
                    System.out.println(users[5]);
                    System.out.println(users[6]);
                    System.out.println(users[7]);
                    System.out.println(users[8]);
                    System.out.println(users[9]);


                }

            }


        } catch (Exception e) {
            //System.out.println(e);
        }
        return "ok";
    }

    public void uploadFileUtil(byte[] file, String imgPath, String imgName) throws Exception {
        File targetFile = new File(imgPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(imgPath + imgName, false);//覆盖
        out.write(file);
        out.flush();
        out.close();
    }

}
