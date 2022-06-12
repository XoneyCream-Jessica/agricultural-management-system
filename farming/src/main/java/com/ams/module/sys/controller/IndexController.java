package com.ams.module.sys.controller;

import cn.hutool.json.JSONException;
import com.ams.util.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ProjectName: AMS
 * @ClassName: IndexController
 * @Author: chenxi
 * @Description: 基础Controller
 * @Date: 2022-05-07 22:08
 */
@Scope("prototype")
@Controller
public class IndexController {
    @RequestMapping("/")
    public String home() {
        return "index.html";
    }
    @Value("${web.upload-path}")
    private String path;

    //上传图片
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public @ResponseBody JsonResult uploads(@RequestParam("file") MultipartFile head) throws JSONException, IOException {
        if(head!=null){
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String imgname= UUID.randomUUID().toString() + head.getOriginalFilename();
            path = path + imgname;
            file = new File(path);
            head.transferTo(file);
            Map<String,String> map=new HashMap<>();
            map.put("path","/"+imgname);
            return JsonResult.success(map);
        }else{
            return null;
        }
    }
}