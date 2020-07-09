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
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yan
 */
@Service
public class MultiplyDoUploadServiceImpl implements DoUploadService {
    @Autowired
    private FtpProperties ftpProperties;

    @Override
    public Map<String, Object> doUploadMultipartFile(MultipartFile[] multipartFiles) {
        Map<String, Object> resultMap = new HashMap<>();
        String headAll = "";
        //根据id调用工具类生成新文件名
        String newFileName = FileNameUtil.getFileName(UUID.randomUUID().toString());

        //对文件数组进行遍历
        for (MultipartFile multipartFile : multipartFiles) {
            //老文件名
            String oldFileName = multipartFile.getOriginalFilename();
            //截取老文件名的后缀
            String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
            //将后缀放在新文件名的后面
            newFileName = newFileName + substring;
            //生成路径
            String filePath = LocalDateTime.now().toString();
            //上传
            try {
                Boolean resultBoolean = FtpUtil.uploadFile(ftpProperties.getHost(), Integer.parseInt(ftpProperties.getPort()), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, multipartFile.getInputStream());
                //判断是否上传成功
                if (resultBoolean) {
                    //上传成功
                    String head = ftpProperties.getHttpPath() + File.separator + filePath + File.separator + newFileName;
                    //将当前图片的地址加进headAll，组合成一个新字符串,每个地址中间用\隔开
                    headAll = headAll + ";" + head;
                } else {
                    //如果没有上传成功
                    resultMap.put(StatusEnum.ERROR.getCodeName(), StatusEnum.ERROR.getCode());
                    resultMap.put(StatusEnum.SUCCESS.getResultName(), headAll);
                    //只要有一个没有上传成功，直接结束上传，不再继续
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //如果更新成功
        resultMap.put(StatusEnum.SUCCESS.getCodeName(), StatusEnum.SUCCESS.getCode());
        resultMap.put(StatusEnum.SUCCESS.getResultName(), headAll);
        return resultMap;
    }

    @Override
    public Map<String, Object> doUpload(MultipartFile multipartFile) {
        return null;
    }

}
