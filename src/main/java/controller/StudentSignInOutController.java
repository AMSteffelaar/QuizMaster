package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.paint.Paint;
import model.Database.GroupDAO;
import model.entity.Group;
import model.entity.Session;
import view.SceneManager;

import java.util.ArrayList;

public class StudentSignInOutController {
    SceneManager manager = SceneManager.getSceneManager();
    GroupDAO gdao = GroupDAO.getInstance();

    private int studentId = Session.getInstance().getCurrentUser().getId();
    ArrayList<Group> ingeschreven_groepen = gdao.studentInGroups(studentId);

    @FXML
    private Button signInButton;

    @FXML
    private Button signOutButton;

    @FXML
    private ListView<Group> courseList = new ListView(); //is de courselist not signed in

    @FXML
    private ListView<Group> SignedInCourseList = new ListView();


    public void setup() {
        signInButton.setTextFill(Paint.valueOf("DarkGreen")); //bepaalt kleur van tekst
        signInButton.setStyle("-fx-background-color: Green");//bepaalt kleur van inschrijfknop
        signOutButton.setTextFill(Paint.valueOf("Black"));//bepaalt kleur tekst uitschrijfknop
        signOutButton.setStyle("-fx-background-color: Red");//bepaalt kleur uitschrijfknop
        populateCourselist();
        courseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        populateCourseList_not_signed_in();
        SignedInCourseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    public void doSignIn(ActionEvent event) {
        ObservableList<Group> keuzeInschrijven = courseList.getSelectionModel().getSelectedItems();
        for (Group g : keuzeInschrijven) {
            gdao.enrollStudentInGroup(g, studentId);
            manager.showStudentSignInOutScene();
        }
    }

    public void doSignOut(ActionEvent event) {
        ObservableList<Group> keuzeUitschrijven = SignedInCourseList.getSelectionModel().getSelectedItems();
        for (Group g : keuzeUitschrijven) {
            gdao.withdrawStudentFromGroup(g, studentId);
            populateCourseList_not_signed_in();
            manager.showStudentSignInOutScene();
        }
    }

    /**
     * Helper methode om linker listview te vullen met Cursussen waarvoor de student die is ingelogd zich heeft ingeschreven.
     * dit is te herleiden door een notering in de koppeltabel studentsingroup, waarna op basis van groupid de bijpassende course kan worden getoond
     */
    private void populateCourselist() { // mag ook heten signedIn courses
        ObservableList<Group> groepen = FXCollections.observableArrayList();
        groepen.setAll(ingeschreven_groepen);
        SignedInCourseList.setItems(groepen);
    }

    /**
     * Helper methode om een lijst te creëren met vermelding van alle groepen (en daarmee ook cursussen) waarvoor de student zich nog niet heeft ingeschreven.
     */
    private void populateCourseList_not_signed_in() {
        ObservableList<Group> cursus = FXCollections.observableArrayList();
        ArrayList<Group> groups = gdao.getGroups();
        // met een loop in een loop nagaan in welke groep de student niet zit,
        // en deze toevoegen in de lijst cursus
        ArrayList<Group> groups_not_signed_in = new ArrayList<>();
        for (Group g : groups) {
            // kijk of g voorkomt in de lijst van de student
            // maak een boolean komt voor, zet die op false
            boolean komt_voor = false;
            for (Group g_this_student : ingeschreven_groepen) {
                if (g.getIdGroup() == g_this_student.getIdGroup()) {
                    komt_voor = true;
                }
            }
            // als komt_voor nog steeds false dan g op de lijst zetten
            if (komt_voor == false) {
                groups_not_signed_in.add(g);
            } //door naar volgende
        }
        for (Group g : groups_not_signed_in) {
            cursus.add(g);
        }
        courseList.setItems(cursus);
    }
}

