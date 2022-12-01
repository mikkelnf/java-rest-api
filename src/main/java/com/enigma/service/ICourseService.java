package com.enigma.service;

import com.enigma.mdel.Course;

import java.util.List;

public interface ICourseService {
    List<Course> list() throws Exception;

    Course create(Course course) throws Exception;

    Course get(String id) throws Exception;

    List<Course> getBy(String keyword, String data) throws Exception;

    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
}
