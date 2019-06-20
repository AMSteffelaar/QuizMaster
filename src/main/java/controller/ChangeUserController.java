package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.entity.User.Student;
import model.entity.User.User;
import view.Main;
import view.SceneManager;

import java.util.ArrayList;


public class ChangeUserController {

    @FXML
    public MenuButton roleMenuButton = new MenuButton();
    ArrayList<String> menuRoles = new ArrayList<>();
    // In de selectUserContoller moet nog gemaakt worden hier wordt dan een gebruiker geselecteerd.
    // Deze gebruiker moet dan gewijzigd worden. DIT IS DUS NOG NIET AF!
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;


    public void setup(User user) {
        populateRoleMenu();
        nameField.setText(user.getName());
        passwordField.setText(user.getPassword());

        //rol zien die je aanklikt op basis van gekozen rol bijpassende user aanmaken.
    }

    public void doMenu(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }


    public void changeRole() {

    }

    public void populateRoleMenu() {
        menuRoles.add("Student");
        menuRoles.add("Docent");
        menuRoles.add("Coordinator");
        menuRoles.add("Administrator");
        menuRoles.add("Technisch Beheerder");
        for (String role : menuRoles) {
            MenuItem item = new MenuItem();

            item.setText(role);
            item.setOnAction(event -> changeRole());
            roleMenuButton.getItems().add(item);
        }

        TextField een = new TextField("Default selected");


        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                een.setText(((MenuItem) e.getSource()).setText(menuRoles));
            }
        };
            }
}

    /*public void doChangeUser(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        //haal ook de gekozen rol op.
        User updateUser = new
        UserDAO udao = UserDAO.getInstance();
        //
        int iduser = udao.getUserIdByNamePassword(name, password);
        User user = udao.changeUser(name, password);
        if (iduser != 0) {
            SceneManager.getSceneManager().showSelectUserScene();
        } else {
            SceneManager.getSceneManager().showLoginFailedScene();
        }*/