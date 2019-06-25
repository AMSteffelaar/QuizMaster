package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Database.GroupDAO;
import model.entity.Group;
import model.entity.Session;

import java.util.ArrayList;

public abstract class StudentGroupController {

    GroupDAO gdao = GroupDAO.getInstance();
    private int studentId = Session.getInstance().getCurrentUser().getId();
    protected ArrayList<Group> ingeschreven_groepen = gdao.studentInGroups(studentId);

    @FXML
    protected ListView<Group> signedInCourseList = new ListView();

    /**
     * Helper methode om linker listview te vullen met Cursussen waarvoor de student die is ingelogd zich heeft ingeschreven.
     * dit is te herleiden door een notering in de koppeltabel studentsingroup, waarna op basis van groupid de bijpassende course kan worden getoond
     */
    protected void populateCourselist() { // mag ook heten signedIn courses
        ObservableList<Group> groepen = FXCollections.observableArrayList();
        groepen.setAll(ingeschreven_groepen);
        signedInCourseList.setItems(groepen);
    }
}
