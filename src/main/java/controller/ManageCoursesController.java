package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Database.CourseDAO;
import model.entity.Course;
import view.SceneManager;

import java.util.ArrayList;

public class ManageCoursesController {
    private CourseDAO cdao = CourseDAO.getInstance();
    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private ListView courseList = new ListView();

    @FXML
    /**
     * wordt gestart bij het aanroepen van de view via de Scenemanager
     */
    public void setup() {
        populateScreen();
    }

    /**
     * gaat terug naar WelcomeController
     * @param event
     */
    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    /**
     * Linkt door naar NewCourseController
     * @param event
     */
    public void doCreateCourse(ActionEvent event) {
        manager.showNewCourseScene();
    }

    /**
     *  Geeft functionaliteit aan de knop wijzig cursus, kiest geselecteerde cursus uit de lijst, haalt deze op uit de MySQL
     *  database en geeft deze mee naar het scherm ChangeCourseController, zodat de attributen van de cursus aldaar kunnen worden weergegeven.
     *  en de ID kan worden worden gebruikt voor het aanpassen van de cursus.
     * @param event noodzakelijk ivm JavaFX, maar niet in gebruik
     */
    public void doChangeCourse(ActionEvent event) {
        Course course = cdao.getCourseByName((String)courseList.getSelectionModel().getSelectedItem());
        manager.showChangeCourseScene(course);
    }

    /**
     * Zorgt ervoor dat de geselecteerde cursus uit de MySQL database wordt verwijderd
     * @param event noodzakelijk ivm JavaFX, maar niet in gebruik
     */
    public void doDeleteCourse(ActionEvent event) {
        Course course = cdao.getCourseByName((String)courseList.getSelectionModel().getSelectedItem());
        cdao.deleteCourse(course);
        manager.showManageCoursesScene();
    }
    private void populateScreen(){
        ObservableList<String> cursus = FXCollections.observableArrayList();
        ArrayList<Course> courses = cdao.getCourses();
        for (Course c : courses) {
            cursus.add(c.getName());
        }
        courseList.setItems(cursus);
    }
}
