package controller;

import javafx.event.ActionEvent;
import model.Database.UserDAO;
import model.entity.Course;

public class ChangeCourseController extends UpdateCourseController {
    private int courseID;

    public void setup(Course course) {
        courseID = course.getIdCourse();
        nameField.setText(course.getName());
        coordinatorMenuButton.setText(course.getCoordinator().getName());
        populateCoordinatorButton();
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    public void doChangeCourse(ActionEvent event) {
        String naam = coordinatorMenuButton.getText();
        int idcoordinator = UserDAO.getInstance().getUserByName(naam).getId();
        String naamCursus = nameField.getText();
        cdao.updateCourse(courseID, idcoordinator, naamCursus);
        manager.showManageCoursesScene();
    }


}
