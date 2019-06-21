package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Database.CourseDAO;
import model.Database.UserDAO;
import model.entity.Course;
import model.entity.User.User;
import view.SceneManager;
import java.util.ArrayList;


public class NewCourseController extends UpdateCourseController{

  private SceneManager manager = SceneManager.getSceneManager();

  @FXML
  private TextField nameField;

  @FXML
  private ChoiceBox<String> coordinatorChoiceBox;



  public void setup() {
    populateCoordinateChoiceBox();
  }

  //Maak een choice box voor het drop down menu
  public void populateCoordinateChoiceBox() {
    UserDAO udao = UserDAO.getInstance();
    ArrayList<User> coordinators = udao.getUsersByRole("Coordinator");
    for (User c : coordinators) {
      String nameCoordinator = c.getName();
      coordinatorChoiceBox.getItems().add(nameCoordinator);
    }
  }

  public void doMenu(ActionEvent event) {
    //Als op de menu-knop wordt gedrukt, moet de user naar het welkomstscherm
    manager.showWelcomeScene();
  }

  public void doNewCourse(ActionEvent event){
  String name = nameField.getText();
  String coordinatorName = coordinatorMenuButton.getText();
  UserDAO udao = UserDAO.getInstance();
  User coordinator = udao.getUserByName(coordinatorName);
  Course course = new Course(name, coordinator);
  CourseDAO cdao = CourseDAO.getInstance();
  cdao.storeCourse(course);
  manager.showManageCoursesScene();
  }

}
