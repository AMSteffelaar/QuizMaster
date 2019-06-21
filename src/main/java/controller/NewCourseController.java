package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.Database.CourseDAO;
import model.Database.UserDAO;
import view.Main;

public class NewCourseController {

    @FXML
    private TextField nameField;

    @FXML
    private MenuButton coordinatorMenuButton = new MenuButton();

    public void setup() {
        // create menu items
        MenuItem m1 = new MenuItem("coordinator 1");
        MenuItem m2 = new MenuItem("coordinator 2");
        MenuItem m3 = new MenuItem("coordinator 3");

        // add menu items to menu
        coordinatorMenuButton.getItems().add(m1);
        coordinatorMenuButton.getItems().add(m2);
        coordinatorMenuButton.getItems().add(m3);
    }

    public void doMenu(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }

    public void doNewCourse(ActionEvent event) {
        String name = nameField.getText();
    }

}
