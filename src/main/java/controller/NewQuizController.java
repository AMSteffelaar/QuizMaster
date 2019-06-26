package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Database.QuizDAO;
import model.entity.Course;
import model.entity.Quiz;
import view.SceneManager;

public class NewQuizController {
  private SceneManager manager = SceneManager.getSceneManager();

  Course course;

  @FXML
  private Label newQuizLabel = new Label(); //naam van de cursus moet hier komen te staan

  @FXML
  private TextField nameField; //naam van de quiz

  @FXML
  private TextField nrOfQuestionsField;

  @FXML
  private TextField tresholdField;



  public void setup(Course course) {
    newQuizLabel.setText("Maak nieuwe quiz voor de cursus " + course.getName());
    System.out.println("de cursus naam: " + course.getName());
    this.course = course;
  }

  public void doMenu(ActionEvent event) {
    manager.showWelcomeScene();
  }



  public void doNewQuiz(ActionEvent event) {
    String quizname = nameField.getText();
    int numberOfQuestions = Integer.parseInt(nrOfQuestionsField.getText());
    int treshold = Integer.parseInt(tresholdField.getText());
    QuizDAO qdao = QuizDAO.getInstance();
    qdao.storeQuiz(course, quizname, numberOfQuestions, treshold);
    }




/*
    if (naamQuiz == null){
      nameField.setText("Graag een naam invullen");
    }
    else if (aantalVragen == null){
      nrOfQuestionsField.setText("Geef het aantal vragen");
    }
    else if (Cesuur == null) {
      tresholdField.setText("Bepaal de cesuur");
    } else {
      qdao.storeQuiz(naamQuiz, aantalVragen , Cesuur);
      manager.showCoordinatorDashboard();
    }
*/



  }

