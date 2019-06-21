package controller;

import javafx.event.ActionEvent;
import view.SceneManager;

public class ManageGroupsController {

  private SceneManager manager = SceneManager.getSceneManager();

  public void setup() {}

  public void doMenu(ActionEvent event){}

  public void doCreateGroup(ActionEvent event){
    manager.showNewGroupScene();
  }

  public void doChangeGroup(ActionEvent event){}

  public void doDeleteGroup(ActionEvent event){}
}
