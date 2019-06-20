package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import model.entity.Course;
import view.SceneManager;

public class ChangeCourseController {

    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private MenuButton coordinatorMenuButton;

    @FXML
    private Button changeCourseButton;

    public void setup(Course course) {

    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
        System.out.println(event);
        System.out.println("wat een toestand");
    }

    public void doChangeCourse(ActionEvent event) {

        System.out.println("verandering aangebracht");
    }
}
