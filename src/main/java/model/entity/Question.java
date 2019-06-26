package model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Question {
    private int idQA;
    private Quiz quiz;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private ArrayList<Question> questions;
    private char correctAnswer;

    public Question(Quiz quiz, String question, String answerA, String answerB, String answerC, String answerD) {
        this.quiz = quiz;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = 'A';
    }

    public int getIdQA() {
        return idQA;
    }

    public void setIdQA(int idQA) {
        this.idQA = idQA;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);

    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(char correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    //TODO: Afhandelen van nullen in antwoorden.
    public void shuffleAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("A"+answerA);
        answers.add("B"+answerB);
        answers.add("C"+answerC);
        answers.add("D"+answerD);
        Collections.shuffle(answers);
        char letter = 'A';
        for (String answer : answers) {
            char oldLetter = answer.charAt(0);
            if (oldLetter == 'A') {
                correctAnswer = letter;
                break;
            }
            letter++;
        }
        setAnswerA(answers.get(0).substring(1));
        setAnswerB(answers.get(1).substring(1));
        setAnswerC(answers.get(2).substring(1));
        setAnswerD(answers.get(3).substring(1));
    }
}
