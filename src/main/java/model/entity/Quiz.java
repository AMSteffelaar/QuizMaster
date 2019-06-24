package model.entity;

import java.util.ArrayList;

public class Quiz {
    private int idQuiz;
    private Course course;
    private String name;
    private ArrayList<Question> questions;

    public Quiz() {
        super();
    }

    public Quiz(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }
    public void removeQuestion(Question question){
        questions.remove(question);

    }
}
