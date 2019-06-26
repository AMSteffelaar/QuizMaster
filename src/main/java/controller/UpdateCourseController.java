package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Database.CourseDAO;
import model.Database.UserDAO;
import model.entity.Session;
import model.entity.User.Administrator;
import model.entity.User.Coordinator;
import model.entity.User.User;
import view.SceneManager;

import java.util.ArrayList;

public class UpdateCourseController {
    protected CourseDAO cdao = CourseDAO.getInstance();
    protected SceneManager manager = SceneManager.getSceneManager();
    protected User currentuser = Session.getInstance().getCurrentUser();

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

    public void checkRole() {
        if (currentuser instanceof Administrator) {
            manager.showManageCoursesScene();
        } else if (currentuser instanceof Coordinator) {
            manager.showCoordinatorDashboard();
        }
    }
}