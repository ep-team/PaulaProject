package com.eshop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Paula
 */
public interface IFileService {

	/**
	 * 
	 * @param file
	 * @param path
	 * @return
	 */
    String upload(MultipartFile file, String path);
}
