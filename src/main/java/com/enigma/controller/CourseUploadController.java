package com.enigma.controller;

import com.enigma.mdel.request.FormDataWithFile;
import com.enigma.mdel.response.SuccessResponse;
import com.enigma.service.CourseUploadService;
import com.enigma.service.interfaces.ICourseUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/course-upload")
public class CourseUploadController {
    private ICourseUploadService courseUploadService;

    public CourseUploadController(@Autowired ICourseUploadService courseUploadService) {
        this.courseUploadService = courseUploadService;
    }

    @PostMapping
    public ResponseEntity upload(FormDataWithFile formDataWithFile){
        MultipartFile file = formDataWithFile.getFile();
        courseUploadService.uploadMaterial(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success upload course", file.getOriginalFilename()));
    }

    @GetMapping
    public ResponseEntity download(@RequestParam String fileName){
        Resource file = courseUploadService.downloadMaterial(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
