package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.entity.Session;
import view.Main;
import java.time.LocalTime;

public class WelcomeController {

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
                    ", U bent ingelogd als " + Session.getInstance().getCurrentUser().getRole());
        } else if (timeNow.isBefore(LocalTime.of(18,0,0))) {
            welcomeLabel.setText("Goedemiddag " + Session.getInstance().getCurrentUser().getName() +
                    ", U bent ingelogd als " + Session.getInstance().getCurrentUser().getRole());
        } else if (timeNow.isBefore(LocalTime.of(23,59,59))) {
            welcomeLabel.setText("Goedenavond " + Session.getInstance().getCurrentUser().getName() +
                    ", U bent ingelogd als " + Session.getInstance().getCurrentUser().getRole());
        } else {
            welcomeLabel.setText("Goedenacht " + Session.getInstance().getCurrentUser().getName() +
                    ", U bent ingelogd als " + Session.getInstance().getCurrentUser().getRole());
        }
    }

    //Nog niet af!
//    public void populateWelcomeMenu() {
//
//
//    }

    public void doLogout(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }
}
