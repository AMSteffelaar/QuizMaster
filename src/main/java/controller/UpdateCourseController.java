package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.Database.CourseDAO;
import model.Database.UserDAO;
import model.entity.Course;
import model.entity.User.Coordinator;
import model.entity.User.User;
import view.SceneManager;

import java.util.ArrayList;

public class UpdateCourseController {
    protected CourseDAO cdao = CourseDAO.getInstance();
    protected SceneManager manager = SceneManager.getSceneManager();

    @FXML
    protected ChoiceBox<String> coordinatorChoiceBox;

    @FXML
    protected TextField numberField;

    @FXML
    protected TextField nameField;


        public void populateCoordinatorChoiceBox() {
            UserDAO udao = UserDAO.getInstance();
            ArrayList<User> users = udao.getUsersByRole("Coordinator");
            for (User user : users) {
                String nameCoordinator = user.getName();
                coordinatorChoiceBox.getItems().add(nameCoordinator);
            }
        }
}