package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.CourseType;
import com.enigma.repository.interfaces.ICourseTypeRepository;
import com.enigma.service.interfaces.ICourseTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseTypeService implements ICourseTypeService {

    @Autowired
    private ICourseTypeRepository courseTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CourseType> list() throws Exception {
        List<CourseType> courseTypeList = courseTypeRepository.findAll();
        return courseTypeList;
    }

    @Override
    public CourseType create(CourseType courseType) throws Exception {
        try {
            CourseType newCourseType = courseTypeRepository.save(courseType);
            return newCourseType;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }

    @Override
    public CourseType get(Integer id) throws Exception {
        Optional<CourseType> courseType = courseTypeRepository.findById(id);
        if(courseType.isEmpty()){
            throw new NotFoundException("Course type not found");
        }
        return courseType.get();
    }

    @Override
    public Page<CourseType> findByPagination(Integer page, Integer size, String direction, String sortBy) {
        if(page<1){
            throw new NotFoundException("page index must not less than 1");
        }
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageReq = PageRequest.of(page -1 , size, sort);
        Page<CourseType> courseTypeList = courseTypeRepository.findAll(pageReq);
        return courseTypeList;
    }


}
