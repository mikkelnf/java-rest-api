package com.enigma.repository.interfaces;

import com.enigma.mdel.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, String> {

    @Query("SELECT c FROM Course c WHERE c.title LIKE %?1%")
    List<Course> findByTitleContains(String title);

    @Query("SELECT c FROM Course c WHERE c.description LIKE %?1%")
    List<Course> findByDescriptionContains(String description);

    @Query("SELECT c FROM Course c ORDER BY c.title")
    Page<Course> findWithPagination(Pageable pageable);

    @Query(value = "SELECT * FROM m_course c ORDER BY c.title LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Course> findWithPaginationWithoutPageable(int pageSize, int offset);
}
