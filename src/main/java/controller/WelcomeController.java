package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import view.Main;

public class WelcomeController {

  @FXML
  private Label welcomeLabel;

  @FXML
   public void setup() {
    //gok dat hier nog bijv een User in kan worden geladen op basis van de loginController zodat de juiste gegevens kunnen worden verwerkt in het welkomst label
    welcomeLabel.setText("Een hele goede morgen gebruiker ...");
  }

  public void doLogout(ActionEvent event) {
    Main.getSceneManager().showLoginScene();
  }
}
