package controller;

import javafx.event.ActionEvent;
import model.Database.UserDAO;
import model.entity.Course;

public class ChangeCourseController extends UpdateCourseController {

    public void setup(Course course) {
        numberField.setText(String.valueOf(course.getIdCourse()));
        nameField.setText(course.getName());
        populateCoordinatorChoiceBox();
        coordinatorChoiceBox.setValue(course.getCoordinator().getName());
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    public void doChangeCourse(ActionEvent event) {
        int id = Integer.parseInt(numberField.getText());
        String naam = coordinatorChoiceBox.getValue();
        int idcoordinator = UserDAO.getInstance().getUserByName(naam).getId();
        String naamCursus = nameField.getText();
        if (naamCursus.equals("")) {
            nameField.setText("Voer aub een naam in");
        } else {
            cdao.updateCourse(id, idcoordinator, naamCursus);
            manager.showManageCoursesScene();
        }

    }


}
