package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Database.UserDAO;
import view.Main;
import view.SceneManager;

public class LoginController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;


    public void doLogin(ActionEvent event) {
        String naam = nameTextField.getText();
        String password = passwordField.getText();
        UserDAO udao = UserDAO.getInstance();
        // In verband met autoincrement idGebruiker heeft iedere naam password combinatie een unieke id, tenzij het voor gaat komen
        // als er iemand met dezelfde naam hetzelfde password kiest.... Misschien iets om rekening mee te houden bij de implementatie
        // van de NewUserController. zodat dit niet voor kan gaan komen
        int id = udao.getUserIdByNamePassword(naam, password);
        if (id != 0) {
            SceneManager.getSceneManager().showWelcomeScene();
        } else {
            SceneManager.getSceneManager().showLoginFailedScene();
        }

    }

    public void doQuit(ActionEvent event) {
        Main.getPrimaryStage().close();
    }
}
