package com.enigma.repository;

import com.enigma.mdel.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseArrayRepository {
    List<Course> getAll() throws Exception;

    Optional<Course> getById(String id) throws Exception;

    Course create(Course course) throws Exception;

    void update(Course course, String id) throws Exception;

    void delete(String id) throws Exception;
}
