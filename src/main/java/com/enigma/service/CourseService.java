package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.Course;
import com.enigma.repository.interfaces.ICourseRepository;
import com.enigma.service.interfaces.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("2")
    String pageSize;

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

    @Override
    public List<Course> findByTitleContains(String value) {
        List<Course> courses = courseRepository.findByTitleContains(value);
        if(courses.isEmpty()){
            throw new NotFoundException("Course with " + value + " title is not found");
        }
        return courses;
    }

    @Override
    public List<Course> findByDescriptionContains(String value) {
        List<Course> courses = courseRepository.findByDescriptionContains(value);
        if(courses.isEmpty()){
            throw new NotFoundException("Course with " + value + " description is not found");
        }
        return courses;
    }

    @Override
    public Page<Course> findByPagination(String page, String pageSize) {
        Pageable pageReq = PageRequest.of(Integer.parseInt(page) -1 , Integer.parseInt(pageSize));

        Page<Course> courseList = courseRepository.findWithPagination(pageReq);

        return courseList;
    }

    @Override
    public List<Course> findByPaginationWithoutPageable(String page, String size) {
        int offset = (Integer.parseInt(page)-1)*Integer.parseInt(size);
        int pageSize = Integer.parseInt(size);
        return courseRepository.findWithPaginationWithoutPageable(pageSize, offset);
    }
}
