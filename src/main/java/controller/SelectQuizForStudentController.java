package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.SceneManager;

public class SelectQuizForStudentController {
  SceneManager manager = SceneManager.getSceneManager();

  @FXML
  private Button doQuizButton;

  public void doQuiz() {}

  public void setup() {
  }

  public void doMenu() {
    manager.showWelcomeScene();
  }
}
