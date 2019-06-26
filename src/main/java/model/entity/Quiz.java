package model.entity;

import java.util.ArrayList;

public class Quiz {
    private int numberOfQuestions;
    private int treshold;
    private int idQuiz;
    private Course course;
    private String name;
    private ArrayList<Quiz> quizes;

    public Quiz() {
        super();
    }

    public Quiz(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public Quiz(int numberOfQuestions, int treshold, Course course, String name) {
        this.numberOfQuestions = numberOfQuestions;
        this.treshold = treshold;
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

    public void addQuiz(Quiz quiz) {
        this.quizes.add(quiz);
    }

    public void removeQuiz(Quiz quiz) {
        this.quizes.remove(quiz);
    }

    @Override
    public String toString() {
        return name ;
    }
}
