package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entity.Course;
import view.SceneManager;

public class NewQuizController {
  SceneManager manager = SceneManager.getSceneManager();

  @FXML
  private Label newQuizLabel;

  @FXML
  private TextField nameField;

  @FXML
  private TextField nrOfQuestionsField;

  @FXML
  private TextField tresholdField;

  public void setup(Course course) {}

  public void doMenu(ActionEvent event) {
    manager.showWelcomeScene();
  }

  public void doNewQuiz(ActionEvent event) {}
}
