package com.enigma.service.interfaces;

import com.enigma.model.CourseInfo;

import java.util.List;

public interface ICourseInfoService {
    List<CourseInfo> list() throws Exception;

    CourseInfo create(CourseInfo courseInfo) throws Exception;

    CourseInfo get(String id) throws Exception;
}
