package controller;

import javafx.event.ActionEvent;
import view.SceneManager;

public class NewGroupController {

    private SceneManager manager = SceneManager.getSceneManager();

    public void setup() {
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    public void doCreateGroup(ActionEvent event) {

    }
}
