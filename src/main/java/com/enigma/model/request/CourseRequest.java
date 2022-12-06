package com.enigma.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CourseRequest {

    @NotBlank(message = "{invalid.title.required}")
    @NotNull
    private String title;
    private String description;
    @NotBlank(message = "{invalid.link.required}")
    @NotNull
    private String link;

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

    @Override
    public String toString() {
        return "CourseRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
