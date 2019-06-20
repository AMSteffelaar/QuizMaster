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
    private CourseDAO cdao = CourseDAO.getInstance();

    @FXML
    private ListView courseList = new ListView();

    @FXML
    public void setup() {

        ObservableList<String> cursus = FXCollections.observableArrayList();
        ArrayList<Course> courses = cdao.getCourses();
        for (Course c : courses) {
            cursus.add(c.getName());
        }
        courseList.setItems(cursus);
    }

    public void doMenu(ActionEvent event) {
        SceneManager.getSceneManager().showWelcomeScene();
    }

    public void doCreateCourse(ActionEvent event) {
        SceneManager.getSceneManager().showNewCourseScene();
    }

    public void doChangeCourse(ActionEvent event) {
        //gemaakt door Joost Kager
        Course course = cdao.getCourseByName((String)courseList.getSelectionModel().getSelectedItem());
        SceneManager.getSceneManager().showChangeCourseScene(course);
    }

    public void doDeleteCourse(ActionEvent event) {
        //courseDAO drop/deletefunctie wordt nog gemaakt door Arnout
    }
}
