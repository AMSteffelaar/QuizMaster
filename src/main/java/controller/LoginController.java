package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Database.DbLauncher;
import model.Database.UserDAO;
import view.SceneManager;

public class LoginController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;


    public void doLogin(ActionEvent event) {
        String naam = nameTextField.getText();
        String password = passwordField.getText();
        DbLauncher db = new DbLauncher();
        UserDAO udao = db.getUdao();
        db.getDb().openConnection();
        int id = udao.getUserIdByNamePassword(naam, password);
        db.getDb().closeConnection();
        if (id != 0) {
            SceneManager.getSceneManager().showWelcomeScene();
        } else {
            SceneManager.getSceneManager().showLoginFailedScene();
        }

    }

    public void doQuit(ActionEvent event) {
        System.out.println("Waarom wil je stoppen?");
    }
}
