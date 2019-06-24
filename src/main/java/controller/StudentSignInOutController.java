package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Database.CourseDAO;
import model.Database.GroupDAO;
import model.entity.Course;
import model.entity.Group;
import model.entity.Session;
import view.SceneManager;

import java.util.ArrayList;

public class StudentSignInOutController {
  SceneManager manager = SceneManager.getSceneManager();
  CourseDAO cdao = CourseDAO.getInstance();
  GroupDAO gdao = GroupDAO.getInstance();

  private int studentId = Session.getInstance().getCurrentUser().getId();
  ArrayList<Group> ingeschreven_groepen = gdao.studentInGroups(studentId);

  @FXML
  private Button signInButton;

  @FXML
  private Button signOutButton;

  @FXML
  private ListView courseList = new ListView(); //is de courselist not signed in

  @FXML
  private ListView SignedInCourseList = new ListView();


  public void setup() {
    populateCourselist();
    populateCourseList_not_signed_in();
  }

  public void doMenu(ActionEvent event){
    manager.showWelcomeScene();
  }

  public void doSignIn(ActionEvent event){
    System.out.println(courseList.getSelectionModel().getSelectedItems());
  }

  public void doSignOut(ActionEvent event){
    System.out.println(SignedInCourseList.getSelectionModel().getSelectedItems());
  }

  private void populateCourselist(){ // mag ook heten signedIn courses
    ObservableList<String> cursus = FXCollections.observableArrayList();
    ;//courses moet zijn groups
    for (Group group : ingeschreven_groepen) {
      cursus.add(group.getCourse().getName());
    }
    SignedInCourseList.setItems(cursus);
  }

  private void populateCourseList_not_signed_in (){
    ObservableList<String> cursus = FXCollections.observableArrayList();
    ArrayList<Group> groups = gdao.getGroups();
    // met een loop in een loop nagaan in welke groep de student niet zit,
    // van zo'n groep de bijbehorende cursus naam in een arrayList met cursussen zetten
    ArrayList<Group> groups_not_signed_in = new ArrayList<>();
    for (Group g : groups ) {
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
      cursus.add(g.getCourse().getName());
    }
    courseList.setItems(cursus);
  }

}

