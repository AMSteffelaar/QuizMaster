package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.Database.CourseDAO;
import model.Database.UserDAO;
import model.entity.User.Coordinator;
import model.entity.User.User;
import view.SceneManager;

import java.util.ArrayList;

public class UpdateCourseController {
    protected CourseDAO cdao = CourseDAO.getInstance();
    protected SceneManager manager = SceneManager.getSceneManager();

    @FXML
    protected TextField nameField;

    @FXML
    protected MenuButton coordinatorMenuButton;

    protected void populateCoordinatorButton() {
        ArrayList<User> users = UserDAO.getInstance().getUsers();
        ArrayList<String> coordinators = getCoordinators(users);
        ObservableList<String> coordinatoren = FXCollections.observableArrayList(coordinators);
        for (String co : coordinatoren) {
            MenuItem item = new MenuItem(co);
            item.setOnAction((new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    coordinatorMenuButton.setText(co);
                }
            }));
            coordinatorMenuButton.getItems().add(item);
        }
    }

    private ArrayList<String> getCoordinators(ArrayList<User> users) {
        ArrayList<String> coordinators = new ArrayList<>();
        for (User u : users) {
            if (u instanceof Coordinator) {
                coordinators.add(u.getName());
            }
        }
        return coordinators;
    }
}
