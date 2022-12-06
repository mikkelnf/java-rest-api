package com.enigma.service.interfaces;

import com.enigma.model.CourseType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICourseTypeService {
    List<CourseType> list() throws Exception;

    CourseType create(CourseType courseType) throws Exception;

    CourseType get(Integer id) throws Exception;

    Page<CourseType> findByPagination(Integer page, Integer size, String direction, String sortBy);


}
