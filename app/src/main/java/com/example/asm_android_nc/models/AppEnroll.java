package com.example.asm_android_nc.models;

public class AppEnroll {
    private Integer id,coursesId,userId;

    public AppEnroll() {
    }

    public AppEnroll(Integer id, Integer coursesId, Integer userId) {
        this.id = id;
        this.coursesId = coursesId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Integer coursesId) {
        this.coursesId = coursesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
