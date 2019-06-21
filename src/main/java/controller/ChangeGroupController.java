package controller;

import javafx.event.ActionEvent;
import model.Database.GroupDAO;
import model.entity.Group;
import view.SceneManager;


public class ChangeGroupController {

  private SceneManager manager = SceneManager.getSceneManager();
  private Group group;

  GroupDAO gdao = GroupDAO.getInstance();


  public void setup(Group group) {
    populateScreen(group);}

  private void populateScreen(Group group){
    manager.showChangeGroupScene(group);
  }

  public void doMenu(ActionEvent event){manager.showWelcomeScene();}

  public void doChangeGroup(ActionEvent event){}
}
