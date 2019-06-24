package model.entity;

import model.entity.User.User;

import java.util.ArrayList;

public class Group {
    private int idGroup;
    private String name;
    private User teacher;
    private Course course;
    private ArrayList<User> students;

    public Group() {
        super();
    }

    public Group(String name, User teacher, Course course) {
        this.name = name;
        this.teacher = teacher;
        this.course = course;
        this.students = new ArrayList<>();
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addStudent(User student) {
        students.add(student);
    }

    public void removeStudent(User student) {
        students.remove(student);
    }

    @Override
    //deze to String wordt gebruikt om de lijsten in de Studentsignin/outcontroller te vullen met leesbare tekst
    public String toString() {
        return String.format("%s", this.course.getName());
    }
}
