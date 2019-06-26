package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Database.GroupDAO;
import model.entity.Group;
import view.SceneManager;

import java.util.ArrayList;

public class ManageGroupsController {
  private SceneManager manager = SceneManager.getSceneManager();
  private GroupDAO gdao = GroupDAO.getInstance();
  private ArrayList<Integer> idgroep = new ArrayList<>();

  @FXML
  private ListView groupList = new ListView();

  public void setup() {
    populateScreen();
  }

  public void doMenu(ActionEvent event){manager.showWelcomeScene();}

  public void doCreateGroup(ActionEvent event){
    manager.showNewGroupScene();
  }

  public void doChangeGroup(ActionEvent event){
    Group group = (Group)groupList.getSelectionModel().getSelectedItem();
    manager.showChangeGroupScene(group);
  }
  public void doDeleteGroup(ActionEvent event){
    Group group = (Group)groupList.getSelectionModel().getSelectedItem();
    gdao.deleteGroup(group);
    manager.showManageGroupsScene();
  }

  private void populateScreen(){
    ObservableList<Group> groepen = FXCollections.observableArrayList();
    ArrayList<Group> groups  = gdao.getGroups();
    groepen.setAll(groups);
    groupList.setItems(groepen);
  }
}
