package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Database.CourseDAO;
import model.Database.GroupDAO;
import model.Database.UserDAO;
import model.entity.Course;
import model.entity.Group;
import model.entity.User.Teacher;
import model.entity.User.User;
import view.SceneManager;

import java.util.ArrayList;

public class NewGroupController {

    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private ChoiceBox<String> teacherChoiceBox;

    public void setup() {
        populateCourseChoiceBox();
        populateTeacherChoiceBox();
    }

    public void populateCourseChoiceBox() {
        CourseDAO cdao = CourseDAO.getInstance();
        ArrayList<Course> courses = cdao.getCourses();
        for (Course course : courses) {
            courseChoiceBox.getItems().add(course.getName());
        }
    }

    public void populateTeacherChoiceBox() {
        UserDAO udao = UserDAO.getInstance();
        ArrayList<User> teachers = udao.getUsersByRole("Teacher");
        for (User teacher : teachers) {
            teacherChoiceBox.getItems().add(teacher.getName());
        }
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    public void doCreateGroup(ActionEvent event) {
        String nameGroup = nameField.getText();
        String teacherName = teacherChoiceBox.getValue();
        UserDAO udao = UserDAO.getInstance();
        User teacher = udao.getUserByName(teacherName);

        String courseName = courseChoiceBox.getValue();
        CourseDAO cdao = CourseDAO.getInstance();
        Course course = cdao.getCourseByName(courseName);

        Group group = new Group(nameGroup, teacher, course);

        GroupDAO gdao = GroupDAO.getInstance();

        //TODO: In GroupDAO is deze uitgecomment.
        //gdao.storeGroup(group);
        manager.showManageGroupsScene();

    }
}
