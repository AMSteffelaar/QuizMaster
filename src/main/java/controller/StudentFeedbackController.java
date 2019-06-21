package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.entity.Quiz;
import view.SceneManager;

public class StudentFeedbackController {
  SceneManager manager = SceneManager.getSceneManager();

  @FXML
  private Label feedbackLabel;

  @FXML
  private ListView feedbackList;

  public void setup(Quiz quiz) {}

  public void doMenu() {
    manager.showWelcomeScene();
  }
}


