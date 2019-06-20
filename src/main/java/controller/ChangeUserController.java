package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Database.RoleDAO;
import model.Database.UserDAO;
import model.entity.User.User;
import view.Main;


import java.util.ArrayList;


public class ChangeUserController {
    private int id;

    @FXML
    public MenuButton roleMenuButton = new MenuButton();
    // In de selectUserContoller moet nog gemaakt worden hier wordt dan een gebruiker geselecteerd.
    // Deze gebruiker moet dan gewijzigd worden. DIT IS DUS NOG NIET AF!
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;


    public void setup(User user) {
        id = UserDAO.getInstance().getUserIdByNamePassword(user.getName(), user.getPassword());
        ArrayList<String> roles = RoleDAO.getInstance().getRoles();
        ObservableList<String> userRoles = FXCollections.observableArrayList(roles);
        for (String role : userRoles) {
            MenuItem item = new MenuItem(role);
            item.setOnAction((new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    roleMenuButton.setText(role);
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
        UserDAO udao = UserDAO.getInstance();
        User user = udao.createUser(name, password, role);
        udao.changeUser(user, id);
        Main.getSceneManager().showSelectUserScene();
    }
}