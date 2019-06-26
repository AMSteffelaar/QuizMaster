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
    private LocalDate date;

    public Answer(Quiz quiz, Student student) {
        this.quiz = quiz;
        this.student = student;
        this.vragen = new Question[quiz.getNumber];
        this.antwoord = new String[quiz.getNumber];
        this.date = LocalDate.now();
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
