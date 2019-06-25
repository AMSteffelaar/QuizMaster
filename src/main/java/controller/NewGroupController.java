package controller;

import javafx.event.ActionEvent;
import model.Database.CourseDAO;
import model.Database.UserDAO;

public class NewGroupController extends UpdateGroupController{

  public void setup() {
    vulCursus();
    vulDocent();
  }

  public void doMenu(ActionEvent event){manager.showWelcomeScene();}

  public void doCreateGroup(ActionEvent event){
    String naamGroep = nameField.getText();
    String naamDocent = teacherMenuButton.getText();
    int docentId = UserDAO.getInstance().getUserByName(naamDocent).getId();
    System.out.println(docentId);
    String naamCursus = courseMenuButton.getText();
    int cursusID = CourseDAO.getInstance().getCourseByName(naamCursus).getIdCourse();
    System.out.println(cursusID);
    if (naamGroep == null){
      nameField.setText("Graag een naam invullen");
    }
    else if (naamDocent == null){
        teacherMenuButton.setText("maak een keuze");
      }
    else if (courseMenuButton == null) {
      courseMenuButton.setText("maak een keuze");
    } else {
      gdao.storeGroup(cursusID,docentId, naamGroep);
      manager.showManageGroupsScene();
    }
  }
}
