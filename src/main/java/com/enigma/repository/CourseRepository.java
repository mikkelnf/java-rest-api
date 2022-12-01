package com.enigma.repository;

import com.enigma.exception.NotFoundException;
import com.enigma.mdel.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//public class CourseRepository implements ICourseRepository{
//    public Optional<List<Course>> getBy(String key, String val){
//        List<Course> courseResults = new ArrayList<>();
//        if(findAll().isEmpty()){
//            throw new NotFoundException();
//        }
//        try {
//            switch (key){
//                case "id":
//                    courseResults = courseRepository.findAll().stream().filter(x->x.getCourseId().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
//                    if(courseResults.isEmpty()){
//                        throw new NotFoundException();
//                    }
//                    return courseResults;
//                case "desc":
//                    courseResults = courseRepository.findAll().stream().filter(x->x.getDescription().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
//                    if(courseResults.isEmpty()){
//                        throw new NotFoundException();
//                    }
//                    return courseResults;
//                case "link":
//                    courseResults = courseRepository.findAll().stream().filter(x->x.getLink().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
//                    if(courseResults.isEmpty()){
//                        throw new NotFoundException();
//                    }
//                    return courseResults;
//                case "title":
//                    courseResults = courseRepository.findAll().stream().filter(x->x.getTitle().equalsIgnoreCase(data.toLowerCase())).collect(Collectors.toList());
//                    if(courseResults.isEmpty()){
//                        throw new NotFoundException();
//                    }
//                    return courseResults;
//                default:
//                    return null;
//            }
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//}
