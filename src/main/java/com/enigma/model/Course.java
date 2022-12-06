package com.enigma.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "m_course")
public class Course {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    private String title;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(name = "link", length = 150)
    private String link;

    @ManyToOne
    @JoinColumn(name = "course_type_id")
    private CourseType courseType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_info_id")
    private CourseInfo courseInfo;

    @Column(name = "file_name", length = 150)
    private String fileName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }
}
