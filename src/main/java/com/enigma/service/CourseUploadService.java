package com.enigma.service;

import com.enigma.repository.interfaces.IFileRepository;
import com.enigma.service.interfaces.ICourseUploadService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CourseUploadService implements ICourseUploadService {
    private IFileRepository fileRepository;

    public CourseUploadService(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void uploadMaterial(MultipartFile multipartFile) {
        fileRepository.store(multipartFile);
    }

    @Override
    public Resource downloadMaterial(String fileName) {
        return fileRepository.load(fileName);
    }
}
