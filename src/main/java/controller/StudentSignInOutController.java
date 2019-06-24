package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Database.CourseDAO;
import model.Database.GroupDAO;
import model.entity.Course;
import model.entity.Group;
import model.entity.Session;
import view.SceneManager;

import java.util.ArrayList;

public class StudentSignInOutController {
  SceneManager manager = SceneManager.getSceneManager();
  CourseDAO cdao = CourseDAO.getInstance();
  GroupDAO gdao = GroupDAO.getInstance();

  private int studentId = Session.getInstance().getCurrentUser().getId();

  @FXML
  private Button signInButton;

  @FXML
  private Button signOutButton;

  @FXML
  private ListView courseList = new ListView();

  private ListView SignedInCourseList = new ListView();


  public void setup() {
    populateCourselist();

  }

  public void doMenu(ActionEvent event){
    manager.showWelcomeScene();
  }

  public void doSignIn(ActionEvent event){}

  public void doSignOut(ActionEvent event){}

  private void populateCourselist(){
    ObservableList<String> cursus = FXCollections.observableArrayList();
    ArrayList<Group> courses = gdao.studentInGroups(studentId);
    for (Group group : courses) {
      System.out.println(group);
      cursus.add(group.getCourse().getName());
    }
    courseList.setItems(cursus);
  }

  private void populateSignedInCourseList (){
    ObservableList<String> cursus = FXCollections.observableArrayList();
    ArrayList<Course> courses = cdao.getCourses();
    for (Course c : courses) {
      cursus.add(c.getName());
    }
    courseList.setItems(cursus);
  }
}

