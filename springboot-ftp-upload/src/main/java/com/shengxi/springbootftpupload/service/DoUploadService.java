package com.shengxi.springbootftpupload.service;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yan
 * @version 1.0.0
 * @date 2019-12-11 10:55:02
 */
public interface DoUploadService {
    Map<String, Object> doUpload(MultipartFile multipartFile);

    Map<String, Object> doUploadMultipartFile(MultipartFile[] multipartFiles);
}
