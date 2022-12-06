package com.enigma.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourseFormRequest {
    @NotBlank(message = "{invalid.title.required}")
    @NotNull
    private String title;
    @NotBlank(message = "{invalid.description.required}")
    @NotNull
    private String description;
    private String link;
    @NotBlank(message = "{invalid.duration.required}")
    @NotNull
    private String duration;
    @NotBlank(message = "{invalid.level.required}")
    @NotNull
    private String level;

    @NotNull(message = "{invalid.courseType.required}")
    private Integer courseTypeId;

    private String fileName;

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

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    @Override
    public String toString() {
        return "CourseRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
