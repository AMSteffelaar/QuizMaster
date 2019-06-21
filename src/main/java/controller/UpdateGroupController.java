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
import model.entity.Course;
import model.entity.User.Coordinator;
import model.entity.User.Teacher;
import model.entity.User.User;
import view.SceneManager;

import java.util.ArrayList;

public class UpdateGroupController {
    protected SceneManager manager = SceneManager.getSceneManager();
    @FXML
    protected TextField nameField;
    @FXML
    protected MenuButton courseMenuButton = new MenuButton();
    @FXML
    protected MenuButton teacherMenuButton = new MenuButton();
    private UserDAO udao = UserDAO.getInstance();
    private CourseDAO cdao = CourseDAO.getInstance();

    protected void vulCursus(){
        ArrayList<String> courses = cursusLijst();
        ObservableList<String> cursussen = FXCollections.observableArrayList(courses);
        for (String c : cursussen) {
            MenuItem item = new MenuItem(c);
            item.setOnAction((new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    courseMenuButton.setText(c);
                }
            }));
            courseMenuButton.getItems().add(item);
        }
    }


    private ArrayList<String> cursusLijst() {
        ArrayList<Course> courses = cdao.getCourses();
        ArrayList<String> cursus = new ArrayList<>();
        for (Course c: courses ) {
            cursus.add(c.getName());
        }
        return cursus;
    }
    protected void vulDocent() {
        ArrayList<User> users = UserDAO.getInstance().getUsers();
        ArrayList<String> teachers = getTeachers(users);
        ObservableList<String> coordinatoren = FXCollections.observableArrayList(teachers);
        for (String co : coordinatoren) {
            MenuItem item = new MenuItem(co);
            item.setOnAction((new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    teacherMenuButton.setText(co);
                }
            }));
            teacherMenuButton.getItems().add(item);
        }
    }

    private ArrayList<String> getTeachers(ArrayList<User> users) {
        ArrayList<String> teachers = new ArrayList<>();
        for (User u : users) {
            if (u instanceof Teacher) {
                teachers.add(u.getName());
            } else if (u instanceof Coordinator) {
                teachers.add(u.getName());
            }
        }
        return teachers;
    }
}


