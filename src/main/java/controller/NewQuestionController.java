package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Database.*;
import model.entity.Question;
import model.entity.Quiz;
import view.SceneManager;


public class NewQuestionController {
    SceneManager manager = SceneManager.getSceneManager();
    Quiz quiz;
    Question question;
    protected QuestionDAO qdao = QuestionDAO.getInstance();
    private int id;
    private int id2;

    @FXML
    protected TextField nameField;

    @FXML
    protected TextField nameField1;

    @FXML
    protected TextField nameField2;

    @FXML
    protected TextField nameField21;

    @FXML
    protected TextField nameField22;

    @FXML
    protected Label QuizNameLabel;



    public void setup(Quiz quiz, Question question) {
        this.quiz = quiz;
        this.question = question;

        QuizNameLabel.setText(String.format("Maak een nieuwe quiz voor %s",quiz.getName()));
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    public void doNewQuestion(ActionEvent event) {
        String vraag = nameField.getText();
        String antwoordA = nameField1.getText();
        String antwoordB = nameField2.getText();
        String antwoordC = nameField21.getText();
        String antwoordD = nameField22.getText();
        Quiz quizes = QuizDAO.getInstance().getQuizById(quiz.getIdQuiz());
        System.out.println(quizes);
        qdao.storeQuestion(quizes.getIdQuiz(), vraag, antwoordA,antwoordB,antwoordC, antwoordD);
        manager.showCoordinatorDashboard();

//        if (vraag == null) {
//            nameField.setText("Graag een vraag invullen");
//        } else if (antwoordA == null) {
//            nameField1.setText("maak een keuze");
//        } else if (antwoordB == null) {
//            nameField2.setText("maak een keuze");
//        } else if (antwoordC == null) {
//            nameField21.setText("maak een keuze");
//        } else if (antwoordD == null) {
//            nameField22.setText("maak een keuze");
//        } else {
//            qdao.storeQuestion(quizId, questionId, name);
//            manager.showCoordinatorDashboard();
//        }

    }
}
