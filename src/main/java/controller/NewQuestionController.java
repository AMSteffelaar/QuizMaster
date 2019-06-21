package controller;

import javafx.event.ActionEvent;
import model.entity.Question;
import model.entity.Quiz;
import view.SceneManager;

public class NewQuestionController {
  SceneManager manager = SceneManager.getSceneManager();


  public void setup(Quiz quiz, Question question) {}

  public void doMenu(ActionEvent event) {
    manager.showWelcomeScene();
  }

  public void doNewQuestion(ActionEvent event) {}

}
