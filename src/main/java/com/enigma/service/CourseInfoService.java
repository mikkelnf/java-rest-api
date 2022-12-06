package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.model.CourseInfo;
import com.enigma.repository.interfaces.ICourseInfoRepository;
import com.enigma.service.interfaces.ICourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoService implements ICourseInfoService {
    @Autowired
    private ICourseInfoRepository courseInfoRepository;

    @Override
    public List<CourseInfo> list() throws Exception {
        List<CourseInfo> courseInfoList = courseInfoRepository.findAll();
        return courseInfoList;
    }

    @Override
    public CourseInfo create(CourseInfo courseInfo) throws Exception {
        try {
            CourseInfo newCourseInfo = courseInfoRepository.save(courseInfo);
            return newCourseInfo;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }

    @Override
    public CourseInfo get(String id) throws Exception {
        return null;
    }
}
