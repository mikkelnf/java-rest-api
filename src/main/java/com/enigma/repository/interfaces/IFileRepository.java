package com.enigma.repository.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface IFileRepository {
    void store(MultipartFile file);
    Resource load(String filename);

}
