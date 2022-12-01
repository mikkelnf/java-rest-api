package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.mdel.Course;
import com.enigma.repository.ICourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Course create(Course course) throws Exception {
        Course newCourse = courseRepository.save(course);
        return newCourse;
    }

    @Override
    public Course get(String id) throws Exception {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty()){
            throw new NotFoundException("Course not fond");
        }
        return course.get();
    }

    @Override
    public List<Course> getBy(String keyword, String data) throws Exception {
        return null;
    }

    @Override
    public void update(Course course, String id) throws Exception {
        Course existingCourse = get(id);
        modelMapper.map(course, existingCourse);
        courseRepository.save(existingCourse);
    }

    @Override
    public void delete(String id) throws Exception {
        Course existingCourse = get(id);
        courseRepository.delete(existingCourse);
    }
}
