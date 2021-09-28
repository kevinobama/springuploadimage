package com.kevingates.uploadimage.controllers;

import com.kevingates.uploadimage.mappers.DevicesMapper;
import com.kevingates.uploadimage.models.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Controller
public class HomeController {



    @GetMapping("/")
    public String index(){

        return "/upload/file";
    }

}
