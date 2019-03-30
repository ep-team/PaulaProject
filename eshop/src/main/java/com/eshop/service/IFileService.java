package com.eshop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Paula
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
