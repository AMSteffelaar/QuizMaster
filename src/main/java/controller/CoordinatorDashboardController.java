package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Database.CourseDAO;
import model.Database.QuizDAO;
import model.entity.Course;
import model.entity.Question;
import model.entity.Quiz;
import model.entity.Session;
import view.SceneManager;

import java.util.ArrayList;
import java.util.Queue;

public class CoordinatorDashboardController {
  SceneManager manager = SceneManager.getSceneManager();
  CourseDAO cdao = CourseDAO.getInstance();
  QuizDAO qdao = QuizDAO.getInstance();

  @FXML
  private Button menuButton;

  @FXML
  private Button newCourseButton;

  @FXML
  private Button editCourseButton;

  @FXML
  private Button newQuizButton;

  @FXML
  private Button editQuizButton;

  @FXML
  private Button newQuestionButton;

  @FXML
  private Button editQuestionButton;

  @FXML
  private ListView<Course> courseList;

  @FXML
  private ListView<String> quizList;

  @FXML
  private ListView<String> questionList;

  public void setup() {
    populateCourses();
    courseList.setCellFactory(param -> new ListCell<Course>() {
      @Override
      protected void updateItem(Course cou, boolean empty) {
        super.updateItem(cou, empty);
        if (empty || cou == null || cou.getName() == null) {
          setText(" ");
        } else {
          setText(cou.getName());
          /*courseList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Course> observable, Course oldValue, Course newValue) -> {*/
          /*if(courseList.isFocused())*/
        }
      }
    });


    courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
      @Override
      public void changed(ObservableValue<? extends Course> observableValue, Course s, Course t1) {
//        System.out.println("Selected item in courseList: " + observableValue + ", " + s + ", " + t1);
        System.out.println(t1);
        populateQuiz(t1);
      }
    });

    quizList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        System.out.println("Selected item in quizList: " + observableValue + ", " + s + ", " + t1);
      }
    });

  }

  public void doMenu() { manager.showWelcomeScene(); }

  public void doNewCourse() {
    manager.showNewCourseScene();
  }

  public void doEditCourse() {
    /*manager.showChangeCourseScene(CourseDAO.getInstance().getCourseByName(courseList.getSelectionModel().getSelectedItem()));*/
  }

  public void doNewQuiz() {
  }

  public void doEditQuiz() {
  }

  public void doNewQuestion() {
  }

  public void doEditQuestion() {
  }

  private void populateCourses() {
    ObservableList<Course> cursus = FXCollections.observableArrayList();
    ArrayList<Course> courses = cdao.getCoursesByCoordinator(Session.getInstance().getCurrentUser().getId());
    cursus.setAll(courses);
    courseList.setItems(cursus);

  }

  private void populateQuiz(Course c) {
    c.getIdCourse();
    System.out.println(c.getIdCourse());
    ObservableList<String> quizeses = FXCollections.observableArrayList();
    ArrayList<Quiz> quizes = qdao.getQuizByCourse(c.getIdCourse());
    for (Quiz q : quizes) {
      quizeses.add(q.getName());
    }
    quizList.setItems(quizeses);
  }
}
  /*private void populateQuestion(Quiz q) {
    q.getIdQuiz();
    ObservableList<String> questions = FXCollections.observableArrayList();
    ArrayList<Question> questions = qdao.getQuestionByQuiz(q.getIdQuiz());
    for (Question qu : questions) {
      questions.add(qu.getQuestion());
    }
    quizList.setItems(quizeses);*/



