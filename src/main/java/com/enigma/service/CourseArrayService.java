package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.mdel.Course;
import com.enigma.repository.CourseArrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseArrayService implements ICourseService {

    @Value("3")
    Integer dataLength;

    @Autowired
    private CourseArrayRepository courseRepository;

    @Override
    public List<Course> list() throws Exception{
        List<Course> courseList = courseRepository.getAll();
        if(courseList.isEmpty()){
            throw new NotFoundException();
        }
        return courseList;
    }

    @Override
    public Course create(Course course) throws Exception{
        if(!(courseRepository.getAll().size() < dataLength)){
            throw new Exception("Data is Full");
        }
        return courseRepository.create(course);
    }

    @Override
    public Course get(String id) throws Exception{
        Optional<Course> course = courseRepository.getById(id);
        if(course.isEmpty()){
            throw new NotFoundException();
        }
        return null;
    }

    @Override
    public List<Course> getBy(String keyword, String data) throws Exception{
        List<Course> courseResults = new ArrayList<>();
        if(courseRepository.getAll().isEmpty()){
            throw new NotFoundException();
        }
        try {
            switch (keyword){
                case "id":
                    courseResults = courseRepository.getAll().stream().filter(x->x.getCourseId().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
                    return courseResults;
                case "desc":
                    courseResults = courseRepository.getAll().stream().filter(x->x.getDescription().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
                    return courseResults;
                case "link":
                    courseResults = courseRepository.getAll().stream().filter(x->x.getLink().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
                    return courseResults;
                case "title":
                    courseResults = courseRepository.getAll().stream().filter(x->x.getTitle().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
                    return courseResults;
                default:
                    return null;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) throws Exception{
        try {
            Course existingCourse = get(id);
            courseRepository.update(course, existingCourse.getCourseId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) throws Exception{
        try {
            Course course = get(id);

            courseRepository.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> findByTitleContains(String value) {
        return null;
    }

    @Override
    public List<Course> findByDescriptionContains(String value) {
        return null;
    }

    @Override
    public Page<Course> findByPagination(String page) {
        return null;
    }
}
