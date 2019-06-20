package controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Database.UserDAO;
import model.entity.User.User;
import view.Main;
import view.SceneManager;

import java.util.ArrayList;


public class ChangeUserController {

    @FXML
    public MenuButton roleMenuButton;
    ArrayList<String> menuRoles = new ArrayList<>();
    // In de selectUserContoller moet nog gemaakt worden hier wordt dan een gebruiker geselecteerd.
    // Deze gebruiker moet dan gewijzigd worden. DIT IS DUS NOG NIET AF!
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;

    public void setup(User user) {
        populateRoleMenu();
    }

    public void doMenu(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }


    public void changeRole(ActionEvent event) throws Exception {

    }

    public void populateRoleMenu(){
        menuRoles.add("Student");
        menuRoles.add("Docent");
        for (String role : menuRoles) {
            {
                MenuItem item = new MenuItem();
                item.setText(role);
                roleMenuButton.getItems().add(item);
            }
        }
    }

    public void doChangeUser(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        UserDAO udao = UserDAO.getInstance();
        //deze is wel anders
        int iduser = udao.getUserByName(name).getId();
        User user = udao.changeUser(name, password);
        if (iduser != 0) {
            SceneManager.getSceneManager().showSelectUserScene();
        } else {
            SceneManager.getSceneManager().showLoginFailedScene();
        }
    }
}