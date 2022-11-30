package com.enigma.repository;

import com.enigma.mdel.Course;
import com.enigma.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository implements ICourseRepository{

    @Autowired
    IRandomStringGenerator randomStringGenerator;

    private List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> getById(String id) throws Exception {
        for(Course existingCourse : courses){
            if(existingCourse.getCourseId().equals(id)){
                return Optional.of(existingCourse);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for(Course existingCourse : courses){
            if(existingCourse.getCourseId().equals(id)){
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for(Course course : courses){
            if(course.getCourseId().equals(id)){
                courses.remove(course);
                break;
            }
        }
    }
}
