package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Database.GroupDAO;
import model.Database.QuizDAO;
import model.entity.Quiz;
import model.entity.Session;
import view.SceneManager;
import model.entity.Group;

import java.util.ArrayList;

public class SelectQuizForStudentController extends StudentGroupController {

  SceneManager manager = SceneManager.getSceneManager();
  GroupDAO gdao = GroupDAO.getInstance();
  private int studentId = Session.getInstance().getCurrentUser().getId();
  QuizDAO qdao = QuizDAO.getInstance();

  @FXML
  private Button doQuizButton;

  @FXML
  private ListView<Quiz> userList = new ListView<>();

  public void doQuiz() {
     Quiz quiz = userList.getSelectionModel().getSelectedItem();
    System.out.print("de quiz is: " + quiz);
     manager.showFillOutQuiz(quiz);
  }

  public void setup() {
    populateQuizList();
  }

  public void populateQuizList() {
    ArrayList<Quiz> quizList = new ArrayList<>();
    for (Group g: ingeschreven_groepen) {
      // haal uit de db de bij de course_idCourse horende quizen, gebruik getQuizByCourse
      quizList.addAll(qdao.getQuizByCourse(g.getCourse().getIdCourse()));
      }
    ObservableList<Quiz> quizzes = FXCollections.observableArrayList();
    quizzes.setAll(quizList);
    userList.setItems(quizzes);
  }


  public void doMenu() {
    manager.showWelcomeScene();
  }
}
