package com.enigma.controller;


import com.enigma.mdel.Course;
import com.enigma.mdel.request.CourseRequest;
import com.enigma.mdel.response.ErrorResponse;
import com.enigma.mdel.response.SuccessResponse;
import com.enigma.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
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

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest courseRequest) throws Exception{
        try {
            Course newCourse = modelMapper.map(courseRequest, Course.class);
            Course result = courseService.create(newCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
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
}
