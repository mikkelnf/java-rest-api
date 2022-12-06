package com.enigma.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "m_course_type")
public class CourseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_type_id")
    private Integer courseTypeId;

    @Column(name = "type_name", unique = true)
    @NotBlank(message = "{invalid.typeName.required}")
    @NotNull
    private String typeName;

//    @OneToMany(mappedBy = "courseType")
//    private List<Course> course;

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "courseTypeId=" + courseTypeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    //    @JsonView(CourseType.Views.Internal.class)
//    public List<Course> getCourse() {
//        return course;
//    }
//
//    public void setCourse(List<Course> course) {
//        this.course = course;
//    }

//    public static final class Views {
//        // show only public data
//        public interface Public {}
//
//        // show public and internal data
//        public interface Internal extends Public {}
//    }
}
