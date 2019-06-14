package controller;

import javafx.event.ActionEvent;
import view.Main;
import view.SceneManager;

public class LoginFailedController {

  public void doTryAgain(ActionEvent event) {
      SceneManager.getSceneManager().showLoginScene();
      ;

  }

  public void doQuit(ActionEvent event) {
      Main.getPrimaryStage().close();
  }
}
