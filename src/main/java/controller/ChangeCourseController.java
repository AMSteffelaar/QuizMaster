package controller;

import javafx.event.ActionEvent;
import model.Database.UserDAO;
import model.entity.Course;
import model.entity.User.Administrator;
import model.entity.User.Coordinator;
import model.entity.User.User;

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
//        String passwordCoordinator = UserDAO.getInstance().getUserByName(naam).getPassword();
        String naamCursus = nameField.getText();
        if (naamCursus.equals("")) {
            nameField.setText("Voer aub een naam in");
        } else {
            User user = UserDAO.getInstance().getUserByName(naam);
            Course course = new Course(id,naamCursus,user);
            cdao.storeCourse(course);
            checkRole();
        }
    }


}
