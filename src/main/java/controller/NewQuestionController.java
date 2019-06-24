package controller;

import javafx.event.ActionEvent;
import model.entity.Question;
import model.entity.Quiz;
import view.SceneManager;

public class NewQuestionController {
  SceneManager manager = SceneManager.getSceneManager();
  Quiz quiz;
  Question question;


  public void setup(Quiz quiz, Question question) {
    this.quiz = quiz;
    this.question = question;

  }

  public void doMenu(ActionEvent event) {
    manager.showWelcomeScene();
  }

  public void doNewQuestion(ActionEvent event) {}

}
