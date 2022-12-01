package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.mdel.Course;
import com.enigma.repository.ICourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class CourseService implements ICourseService{

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Course> list() throws Exception {
        List<Course> courseList = courseRepository.findAll();
        return courseList;
    }

    @Override
    public Course create(Course course){
        try {
            Course newCourse = courseRepository.save(course);
            return newCourse;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }

    @Override
    public Course get(String id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty()){
            throw new NotFoundException("Course not fond");
        }
        return course.get();
    }

    @Override
    public List<Course> getBy(String keyword, String data) throws Exception {
        List<Course> courseResults = new ArrayList<>();
        if(courseRepository.findAll().isEmpty()){
            throw new NotFoundException();
        }
        try {
            switch (keyword){
                case "id":
                    courseResults = courseRepository.findAll().stream().filter(x->x.getCourseId().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
                    if(courseResults.isEmpty()){
                        throw new NotFoundException();
                    }
                    return courseResults;
                case "desc":
                    courseResults = courseRepository.findAll().stream().filter(x->x.getDescription().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
                    if(courseResults.isEmpty()){
                        throw new NotFoundException();
                    }
                    return courseResults;
                case "link":
                    courseResults = courseRepository.findAll().stream().filter(x->x.getLink().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
                    if(courseResults.isEmpty()){
                        throw new NotFoundException();
                    }
                    return courseResults;
                case "title":
                    courseResults = courseRepository.findAll().stream().filter(x->x.getTitle().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
                    if(courseResults.isEmpty()){
                        throw new NotFoundException();
                    }
                    return courseResults;
                default:
                    return null;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) throws Exception {
        try {
            Course existingCourse = get(id);
            if(course.getCourseId() == null){
                course.setCourseId(existingCourse.getCourseId());
            }
            courseRepository.save(course);
        }catch (NotFoundException e){
            throw new NotFoundException("Update Failed because ID is not found");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            Course existingCourse = get(id);
            courseRepository.delete(existingCourse);
        }catch (NotFoundException e){
            throw new NotFoundException("Update Failed because ID is not found");
        }
    }
}
