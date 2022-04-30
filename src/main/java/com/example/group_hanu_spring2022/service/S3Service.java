package com.example.group_hanu_spring2022.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadFile(MultipartFile multipartFile);

    byte[] getFile(String id);

    String deleteFile(String id);
}
