package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import model.Database.CourseDAO;
import model.Database.UserDAO;
import model.entity.Course;
import model.entity.User.User;
import view.Main;
import view.SceneManager;

import java.util.ArrayList;


public class NewCourseController extends UpdateCourseController {

    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private TextField nameField;


    public void setup() {
        populateCoordinatorChoiceBox();
    }

    public void doMenu(ActionEvent event) {
        //Als op de menu-knop wordt gedrukt, moet de user naar het welkomstscherm
        manager.showWelcomeScene();
    }

    public void doNewCourse(ActionEvent event) {
        String name = nameField.getText();
        String coordinatorName = coordinatorChoiceBox.getValue();
        UserDAO udao = UserDAO.getInstance();
        User coordinator = udao.getUserByName(coordinatorName);
        Course course = new Course(name, coordinator);
        CourseDAO cdao = CourseDAO.getInstance();
        cdao.storeCourse(course);
        checkRole();
    }


}
