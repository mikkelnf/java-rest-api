package com.enigma.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface ICourseUploadService {
    void uploadMaterial(MultipartFile multipartFile);
    Resource downloadMaterial(String fileName);
}
