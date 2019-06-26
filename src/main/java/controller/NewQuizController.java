package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entity.Course;
import view.SceneManager;

public class NewQuizController {
  private SceneManager manager = SceneManager.getSceneManager();

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

  }

  public void doMenu(ActionEvent event) {
    manager.showWelcomeScene();
  }

  public void doNewQuiz(ActionEvent event) {
  String naamQuiz = nameField.getText();
  String aantalVragen = nrOfQuestionsField.getText();
  String Cesuur = tresholdField.getText();




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
}
