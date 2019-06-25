package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.entity.Quiz;
import view.SceneManager;

public class FillOutQuizController {
  SceneManager manager = SceneManager.getSceneManager();

  @FXML
  private Label title;

  @FXML
  private Button answerAButton;

  @FXML
  private Button answerBButton;

  @FXML
  private Button answerCButton;

  @FXML
  private Button answerDButton;

  @FXML
  private Button nextQuestionButton;

  @FXML
  private Button previousQuestionButton;

  @FXML
  private Button menuButton;

  public void setup(Quiz quiz) {}

  public void registerA() {}

  public void registerB() {}

  public void registerC() {}

  public void registerD() {}

  public void doNextQuestion() {}

  public void doPreviousQuestion() {}

  public void doMenu() {
    manager.showWelcomeScene();
  }
}
