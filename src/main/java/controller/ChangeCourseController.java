package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Database.CourseDAO;
import model.Database.UserDAO;
import model.entity.Course;
import model.entity.User.Coordinator;
import model.entity.User.User;
import view.SceneManager;

import java.util.ArrayList;

public class ChangeCourseController {

    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private ChoiceBox<String> coordinatorChoiceBox;

    @FXML
    private TextField numberField;

    @FXML
    private TextField nameField;

    public void setup(Course course) {
        numberField.setText(String.valueOf(course.getIdCourse()));
        nameField.setText(course.getName());
        populateCoordinatorChoiceBox();
        coordinatorChoiceBox.setValue(course.getCoordinator().getName());

    }

    public void populateCoordinatorChoiceBox() {
        UserDAO udao = UserDAO.getInstance();
        ArrayList<User> users = udao.getUsersByRole("Coordinator");
        for (User user : users) {
            String nameCoordinator = user.getName();
            coordinatorChoiceBox.getItems().add(nameCoordinator);
        }
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();


    }

    public void doChangeCourse(ActionEvent event) {
        int id = Integer.parseInt(numberField.getText());
        String nameCourse = nameField.getText();
        String coordinatorName = coordinatorChoiceBox.getValue();
        UserDAO udao = UserDAO.getInstance();
        User coordinator = udao.getUserByName(coordinatorName);

        Course course = new Course(id, nameCourse, coordinator);

        CourseDAO cdao = CourseDAO.getInstance();
        //TODO: Nog even met Arnout naar CourseDAO kijken.
        cdao.storeCourse(course);
        //cdao.updateCourse(course,course.getName(),course.getCoordinator());
        manager.showManageCoursesScene();
    }
}
