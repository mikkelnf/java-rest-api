package com.enigma.repository;

import com.enigma.mdel.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, String> {

}
