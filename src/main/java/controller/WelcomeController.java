package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.entity.Session;
import model.entity.User.*;
import view.Main;
import view.SceneManager;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;

public class WelcomeController {

    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private MenuButton taskMenuButton;

    @FXML
    private Label welcomeLabel;


    /**
     * Geeft een verschillende welkomstboodschap, afhankelijk van de lokale tijd.
     * Verwelkomt de currentUser met zijn naam en functie.
     */
    @FXML
    public void setup() {
        LocalTime timeNow = LocalTime.now();
        if (timeNow.isAfter(LocalTime.of(7,0,0)) &&
                timeNow.isBefore(LocalTime.of(12,0,0))) {
            welcomeLabel.setText("Goedemorgen " + Session.getInstance().getCurrentUser().getName() +
                    "! U bent ingelogd als " + Session.getInstance().getCurrentUser().getRole());
        } else if (timeNow.isBefore(LocalTime.of(18,0,0))) {
            welcomeLabel.setText("Goedemiddag " + Session.getInstance().getCurrentUser().getName() +
                    "! U bent ingelogd als " + Session.getInstance().getCurrentUser().getRole());
        } else if (timeNow.isBefore(LocalTime.of(23,59,59))) {
            welcomeLabel.setText("Goedenavond " + Session.getInstance().getCurrentUser().getName() +
                    "! U bent ingelogd als " + Session.getInstance().getCurrentUser().getRole());
        } else {
            welcomeLabel.setText("Goedenacht " + Session.getInstance().getCurrentUser().getName() +
                    "! U bent ingelogd als " + Session.getInstance().getCurrentUser().getRole());
        }
        populateTaskMenu(Session.getInstance().getCurrentUser().getRole());
    }

    public void populateTaskMenu(String role) {
        ArrayList<String> files = new ArrayList<>();
        switch (role) {
                case "Teacher":
                    files.add("");
                    break;
                case "Administrator":

                    break;
                case "SystemAdministrator":

                    break;
                case "Student":

                    break;
                case "Coordinator":

                    break;
            }


            for (String filename : files) {
                if (!filename.equals("windowtool.fxml")) {
                    MenuItem item = new MenuItem();
                    item.setText(filename);
                    item.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            User tech = null;
                            User student = null;
                            User admin = null;
                            switch (filename) {
                                case "welcomeScene.fxml":
                                    manager.showWelcomeScene();
                                    break;
                                case "newUser.fxml":
                                    manager.showNewUserScene();
                                    break;
                                case "selectUser.fxml":
                                    manager.showSelectUserScene();
                                    break;
                                default:
                                    manager.getScene("/view/fxml/" + filename);
                                    break;
                            }
                        }
                    });
                    taskMenuButton.getItems().add(item);
            }
        }
    }
    

    public void doLogout(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }
}
