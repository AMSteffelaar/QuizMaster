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

public class StudentSignInOutController extends StudentGroupController {

    SceneManager manager = SceneManager.getSceneManager();

    private int studentId = Session.getInstance().getCurrentUser().getId();

    @FXML
    private Button signInButton;

    @FXML
    private Button signOutButton;

    @FXML
    private ListView<Group> courseList = new ListView(); //is de courselist not signed in



    public void setup() {
        signInButton.setTextFill(Paint.valueOf("DarkGreen")); //bepaalt kleur van tekst
        signInButton.setStyle("-fx-background-color: Green");//bepaalt kleur van inschrijfknop
        signOutButton.setTextFill(Paint.valueOf("Black"));//bepaalt kleur tekst uitschrijfknop
        signOutButton.setStyle("-fx-background-color: Red");//bepaalt kleur uitschrijfknop
        populateCourselist();
        courseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //zorgt ervoor dat meerdere keuzes mogelijk zijn
        populateCourseList_not_signed_in();
        signedInCourseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    public void doSignIn(ActionEvent event) {
        //haal gemaakte selectie op
        ObservableList<Group> keuzeInschrijven = courseList.getSelectionModel().getSelectedItems();
        //per item in gemaakte keuze, invoegen van record van ingelogde student met group in studentsingroups tabel
        for (Group g : keuzeInschrijven) {
            gdao.enrollStudentInGroup(g, studentId);
            manager.showStudentSignInOutScene();
        }
    }


    public void doSignOut(ActionEvent event) {
        //haal gemaakte selectie op
        ObservableList<Group> keuzeUitschrijven = signedInCourseList.getSelectionModel().getSelectedItems();
        //per item in gemaakte keuze, verwijderen record van ingelogde student met group uit studentsingroups tabel
        for (Group g : keuzeUitschrijven) {
            gdao.withdrawStudentFromGroup(g, studentId);
            populateCourseList_not_signed_in();
            manager.showStudentSignInOutScene();
        }
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

