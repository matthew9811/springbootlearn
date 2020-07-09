package com.shengxi.springbootftpupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping("/file")
    public String index(){
        return "/file_upload";
    }
}
