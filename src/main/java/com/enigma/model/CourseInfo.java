package com.enigma.model;

import javax.persistence.*;

@Entity
@Table(name = "m_course_info")
public class CourseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_info_id")
    private Integer courseInfoId;

    private String duration;

    private String level;

    public Integer getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(Integer courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
