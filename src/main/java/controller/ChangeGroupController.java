package controller;

import javafx.event.ActionEvent;
import model.Database.CourseDAO;
import model.Database.UserDAO;
import model.entity.Group;


public class ChangeGroupController extends UpdateGroupController {
    int groupID;

    public void setup(Group group) {
        groupID = group.getIdGroup();
        vulCursus();
        vulDocent();
        teacherMenuButton.setText(group.getTeacher().getName());
        courseMenuButton.setText(group.getCourse().getName());
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    public void doChangeGroup(ActionEvent event) {
        String naamGroep = nameField.getText();
        String naamDocent = teacherMenuButton.getText();
        int docentId = UserDAO.getInstance().getUserByName(naamDocent).getId();
        System.out.println(docentId);
        String naamCursus = courseMenuButton.getText();
        int cursusID = CourseDAO.getInstance().getCourseByName(naamCursus).getIdCourse();
        System.out.println(cursusID);
        if (naamGroep == null) {
            nameField.setText("Graag een naam invullen");
        } else if (naamDocent == null) {
            teacherMenuButton.setText("maak een keuze");
        } else if (courseMenuButton == null) {
            courseMenuButton.setText("maak een keuze");
        } else {
            gdao.updateGroup(groupID, cursusID, docentId, naamGroep);
        }
    }
}
