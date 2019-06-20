package controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Database.RoleDAO;
import model.Database.UserDAO;
import model.entity.User.Student;
import model.entity.User.User;
import view.Main;
import view.SceneManager;

import java.util.ArrayList;


public class ChangeUserController {

    @FXML
    public MenuButton roleMenuButton = new MenuButton();
    // In de selectUserContoller moet nog gemaakt worden hier wordt dan een gebruiker geselecteerd.
    // Deze gebruiker moet dan gewijzigd worden. DIT IS DUS NOG NIET AF!
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;


    public void setup(User user) {
        ArrayList<String> roles = RoleDAO.getInstance().getRoles();
        ObservableList<String> rollen = FXCollections.observableArrayList(roles);
        for (String rol : rollen) {
            MenuItem item = new MenuItem(rol);
            item.setOnAction((new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    roleMenuButton.setText(rol);
                }
            }));
        }
    }

    public void doMenu(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }


    public void doChangeUser(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        String role = roleMenuButton.getText();
        UserDAO.getInstance().changeUser(name, password, role);
        Main.getSceneManager().showSelectUserScene();
    }
}