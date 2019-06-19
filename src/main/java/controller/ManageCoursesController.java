package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Database.CourseDAO;
import model.entity.Course;
import view.SceneManager;

import java.util.ArrayList;

public class ManageCoursesController {
    private Course course;

    @FXML
    private ListView courseList;

    @FXML
    public void setup() {
        courseList = new ListView<String>();
      CourseDAO courseDAO = CourseDAO.getInstance();
      ArrayList<Course> courses;
      courses = courseDAO.getCourses();
        ObservableList<String> items = FXCollections.observableArrayList(
                "Single", "Double", "Suite", "Family App");
        courseList.setItems(items);


    }

    public void doMenu(ActionEvent event) {
        SceneManager.getSceneManager().showWelcomeScene();
    }

    public void doCreateCourse(ActionEvent event) {
        SceneManager.getSceneManager().showNewCourseScene();
    }

    public void doChangeCourse(ActionEvent event) {
        SceneManager.getSceneManager().showChangeCourseScene(course);
    }

    public void doDeleteCourse(ActionEvent event) {
        //courseDAO drop/deletefunctie
    }
}
