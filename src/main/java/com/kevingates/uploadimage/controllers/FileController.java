package com.kevingates.uploadimage.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
public class FileController {

    @PostMapping("/upload/image")
    public String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam(required = false, name = "categoryId") String categoryId) {
        if(file.isEmpty()) return "上传文件为空";

        Map<String, Object> result = new HashMap<>();
        String fileName = file.getOriginalFilename();

        String sysFileName = UUID.randomUUID().toString().replace("-", "")+fileName.substring(fileName.lastIndexOf("."));
        String dirPath = "/var/tmp/image/";
        File dir = new File(dirPath);
        try {
            if(!dir.exists()) dir.mkdirs();
            String url = dirPath + "/" + sysFileName;
            File dest = new File(url);

            if(!dest.exists()) dest.createNewFile();

            file.transferTo(dest);

            return "success "+dest;
        } catch (IOException e) {
            e.printStackTrace();
            return "上传错误，请重试";
        }
    }
}