package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Database.GroupDAO;
import model.entity.Course;
import model.entity.Group;
import view.SceneManager;

import java.util.ArrayList;

import view.SceneManager;

public class ManageGroupsController {
  private SceneManager manager = SceneManager.getSceneManager();
  private GroupDAO gdao = GroupDAO.getInstance();
  private ArrayList<Integer> idgroep = new ArrayList<>();

  @FXML
  private ListView groupList = new ListView();

  @FXML
  private Label selectLabel = new Label();


  public void setup() {
    populateScreen();
  }

  public void doMenu(ActionEvent event){manager.showWelcomeScene();}

  public void doCreateGroup(ActionEvent event){
    manager.showNewGroupScene();
  }

  public void doChangeGroup(ActionEvent event){
    System.out.println(groupList.getSelectionModel().getSelectedItem());
//    Group group = gdao.getGroupById()ByName((String) courseList.getSelectionModel().getSelectedItem());
//    manager.showChangeCourseScene();
  }
  public void doDeleteGroup(ActionEvent event){}

  private void populateScreen(){
    ObservableList<String> groepen = FXCollections.observableArrayList();
    ArrayList<Group> groups  = gdao.getGroups();
    for (Group g : groups) {
      groepen.add(g.getName());
      idgroep.add(g.getIdGroup());
    }
    groupList.setItems(groepen);
  }
}
