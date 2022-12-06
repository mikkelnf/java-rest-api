package com.enigma.repository.interfaces;

import com.enigma.model.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseTypeRepository extends JpaRepository<CourseType, Integer> {
    @Query("SELECT c FROM CourseType c")
    Page<CourseType> findWithPagination(Pageable pageable);

    List<CourseType> findAll(Specification spec);
}
