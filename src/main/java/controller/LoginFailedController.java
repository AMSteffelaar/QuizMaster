package controller;

import javafx.event.ActionEvent;
import view.Main;
import view.SceneManager;

public class LoginFailedController {
    SceneManager manager = SceneManager.getSceneManager();

  public void doTryAgain(ActionEvent event) {
      manager.showLoginScene();
  }

  public void doQuit(ActionEvent event) {
      Main.getPrimaryStage().close();
  }
}
