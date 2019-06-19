package model.entity;

import model.entity.User.User;

public class Course {
    private int idCourse;
    private String name;
    private User coordinator;

    public Course() {
        super();
    }

    public Course(String name, User coordinator) {
        this.name = name;
        this.coordinator = coordinator;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(User coordinator) {
        this.coordinator = coordinator;
    }


    //Arnout: extra constructor inclusief de uit de db opgehaalde courseId
    public Course(int idCourse, String name, User coordinator){
        this.idCourse = idCourse;
        this.name = name;
        this.coordinator = coordinator;
    }
}
