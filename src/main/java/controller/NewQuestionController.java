package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Database.*;
import model.entity.Course;
import model.entity.Question;
import model.entity.Quiz;
import model.entity.User.User;
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


  public void setup(Quiz quiz, Question question) {
    this.quiz = quiz;
    this.question = question;

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

    int quizId = QuizDAO.getInstance().getQuizById(id).getIdQuiz();
    System.out.println(quizId);
    int questionId = QuestionDAO.getInstance().getQuestionById(id2).getIdQA();
    String name = QuestionDAO.getInstance().getQuestionById(id2).getQuestion();

    if (vraag == null) {
      nameField.setText("Graag een naam invullen");
    } else if (antwoordA == null) {
      nameField1.setText("maak een keuze");
    } else if (antwoordB == null) {
      nameField2.setText("maak een keuze");
    } else if (antwoordC == null) {
      nameField21.setText("maak een keuze");
    } else if (antwoordD == null) {
      nameField22.setText("maak een keuze");
    } else {qdao.storeQuestion(quizId, questionId, name);
      manager.showCoordinatorDashboard();
    }

  }
}
