package com.shengxi.springbootftpupload.controller;

import com.shengxi.springbootftpupload.service.DoUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UpFileController {
    @Autowired
    private DoUploadService doUploadService;

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public void fileUpload(@RequestParam("fileName") MultipartFile file) {
        doUploadService.doUpload(file);
    }
}
