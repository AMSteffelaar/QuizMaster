package model.entity;

import model.entity.User.Student;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Answer {
    private Quiz quiz;
    private Student student;
    private Question[] vragen;
    private String[] antwoord;

    public Answer(Quiz quiz, Student student) {
        this.quiz = quiz;
        this.student = student;
        this.vragen = new Question[quiz.getNumberOfQuestions()];
        this.antwoord = new String[quiz.getNumberOfQuestions()];
        }

    public Quiz getQuiz() {
        return quiz;
    }

    public Student getStudent() {
        return student;
    }

    public Question[] getVragen() {
        return vragen;
    }

    public void setVragen(Question[] vragen) {
        this.vragen = vragen;
    }

    public String[] getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String[] antwoord) {
        this.antwoord = antwoord;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setVragen(Question question, int index) {
        vragen[index] = question;
    }

    public void setAntwoord(String answer, int index) {
        antwoord[index] = answer;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", antwoord[0],antwoord[1], antwoord[2]);
    }
}
