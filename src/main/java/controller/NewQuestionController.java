package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Database.QuestionDAO;
import model.Database.QuizDAO;
import model.entity.Question;
import model.entity.Quiz;
import view.SceneManager;


public class NewQuestionController {
    private QuestionDAO qdao = QuestionDAO.getInstance();
    private QuizDAO quizDAO = QuizDAO.getInstance();
    private SceneManager manager = SceneManager.getSceneManager();
    private Quiz quiz;
    private Question question;

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

        QuizNameLabel.setText(String.format("Maak een nieuwe vraag voor %s", quiz.getName()));
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
        if (correctInput(vraag, antwoordA, antwoordB, antwoordC, antwoordD)) {
            qdao.storeQuestion(quiz.getIdQuiz(), vraag, antwoordA, antwoordB, antwoordC, antwoordD);
            quiz.addQuestion();
            System.out.println(quiz.getIdQuiz());
            quizDAO.updateNumberOfQuestions(quiz);
            manager.showCoordinatorDashboard();
        }
    }

    private boolean correctInput(String vraag, String antwoordA, String antwoordB, String antwoordC, String antwoordD) {
        if (vraag == null) {
            nameField.setText("Graag een vraag invullen");
            return false;
        } else if (antwoordA == null) {
            nameField1.setText("maak een keuze");
            return false;
        } else if (antwoordB == null) {
            nameField2.setText("maak een keuze");
            return false;
        } else if (antwoordC == null) {
            nameField21.setText("maak een keuze");
            return false;
        } else if (antwoordD == null) {
            nameField22.setText("maak een keuze");
            return false;
        }
        return true;
    }
}