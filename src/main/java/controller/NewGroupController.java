package controller;

import javafx.event.ActionEvent;

public class NewGroupController extends UpdateGroupController{

  public void setup() {
    vulDocent();
    vulCursus();
  }

  public void doMenu(ActionEvent event){manager.showWelcomeScene();}

  public void doCreateGroup(ActionEvent event){}
}
