package com.enigma.controller;


import com.enigma.model.Course;
import com.enigma.model.request.CourseRequest;
import com.enigma.model.request.FormDataWithFile;
import com.enigma.model.response.ErrorResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllCourse() throws Exception{
        try {
            List<Course> courses =  courseService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all data", courses));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

//    @GetMapping(params = {"page", "pageSize"})
//    public ResponseEntity getAllCourseWithPagination(@RequestParam String page, @RequestParam String pageSize) throws Exception{
//        try {
//            List<Course> courses =  courseService.findByPagination(page, pageSize).get().collect(Collectors.toList());
//            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all data", courses));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
//        }
//    }

    @GetMapping(params = {"page", "pageSize"})
    public ResponseEntity getAllCourseWithPaginationWithoutPageable(@RequestParam String page, @RequestParam String pageSize) throws Exception{
        try {
            List<Course> courses =  courseService.findByPaginationWithoutPageable(page, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all data", courses));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

//    @PostMapping
//    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest courseRequest) throws Exception{
//        try {
//            Course newCourse = modelMapper.map(courseRequest, Course.class);
//            Course result = courseService.create(newCourse);
//            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
//        }
//    }

    @PostMapping
    public Course createCourse(@Valid FormDataWithFile formDataWithFile, @ModelAttribute CourseRequest courseRequest) throws Exception{
        try {
//            System.out.println(courseRequest);
//            Course result = courseService.create(newCourse);
//            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));
            MultipartFile file = formDataWithFile.getFile();
            String fileName = file.getOriginalFilename();
            Course newCourse = modelMapper.map(courseRequest, Course.class);
            newCourse.setFileName(fileName);
            return newCourse;
        }catch (Exception e){
            throw e;
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) throws Exception{
        try {
            Course course = courseService.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course by id", course));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }

    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getBy(@RequestParam String keyword, @RequestParam String value) throws Exception{
        try {
            List<Course> course = courseService.getBy(keyword, value);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course by", course));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X02", "Course not found"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Course course) throws Exception{
        try {
            courseService.update(course, id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update course", course));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourseById(@PathVariable("id") String id) throws Exception{
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success deleted course", null));
    }
}
