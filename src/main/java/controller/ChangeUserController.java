package controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Database.UserDAO;
import model.entity.User.User;
import view.Main;
import view.SceneManager;


public class ChangeUserController {

    // In de selectUserContoller moet nog gemaakt worden hier wordt dan een gebruiker geselecteerd.
    // Deze gebruiker moet dan gewijzigd worden. DIT IS DUS NOG NIET AF!
    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    public void setup(User user) {

    }

    public void doMenu(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }

    public void changeRole(String name) {


    }

    public void doChangeUser(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        UserDAO udao = UserDAO.getInstance();

        //deze is wel anders
        int iduser = udao.getUserIdByNamePassword(name, password);
        User user = udao.getUserById(iduser);
        if (iduser != 0) {
            SceneManager.getSceneManager().showSelectUserScene();
        } else {
            SceneManager.getSceneManager().showLoginFailedScene();
        }
    }
}
