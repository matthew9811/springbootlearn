package com.shengxi.springbootftpupload.service.impl;

import com.shengxi.springbootftpupload.conf.FtpProperties;
import com.shengxi.springbootftpupload.enums.StatusEnum;
import com.shengxi.springbootftpupload.service.DoUploadService;
import com.shengxi.springbootftpupload.utils.FileNameUtil;
import com.shengxi.springbootftpupload.utils.FtpUtil;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 单文件上传服务层
 *
 * @author yan
 * @version 1.0.0
 * @date 2019-12-11 10:58:27
 * @see com.shengxi.springbootftpupload.service.DoUploadService
 */
@Primary
@Service
public class FileDoUploadServiceImpl implements DoUploadService {

    @Autowired
    FtpProperties ftpProperties;

    @Override
    public Map<String, Object> doUpload(MultipartFile multipartFile) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //老文件名
            String oldFileName = multipartFile.getOriginalFilename();
            //根据id调用工具类生成新文件名
            String newFileName = FileNameUtil.getFileName(UUID.randomUUID().toString());
            //截取老文件名的后缀
            String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
            //将后缀放在新文件名的后面
            newFileName = newFileName + substring;
            //生成路径
            String filePath = LocalDateTime.now().toString();
            //上传
            Boolean resultBoolean = FtpUtil.uploadFile(ftpProperties.getHost(), Integer.parseInt(ftpProperties.getPort()), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, multipartFile.getInputStream());
            //判断是否上传成功
            String head = ftpProperties.getHttpPath() + File.separator + filePath + File.separator + newFileName;

            resultMap.put(StatusEnum.SUCCESS.getCodeName(), StatusEnum.SUCCESS.getCode());
            resultMap.put(StatusEnum.SUCCESS.getResultName(), head);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> doUploadMultipartFile(MultipartFile[] multipartFiles) {
        return null;
    }
}
