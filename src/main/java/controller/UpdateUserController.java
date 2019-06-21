package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.Database.RoleDAO;
import model.Database.UserDAO;
import view.SceneManager;

import java.util.ArrayList;

public class UpdateUserController {
    protected SceneManager manager = SceneManager.getSceneManager();
    UserDAO udao = UserDAO.getInstance();

    @FXML
    protected TextField nameField;

    @FXML
    protected TextField passwordField;

    @FXML
    protected MenuButton roleMenuButton = new MenuButton();

    @FXML
    protected ChoiceBox <String> roleChoiceBox;

    /**
     * Haalt rollen op uit table Role in MySQL DB en vult de Menubutton 'kies Rol'
     * geeft tevens de functionaliteit dat de gekozen rol zichtbaar blijft en kan worden uitgelezen
     * als nieuwe gebruiker wordt aangemaakt.
     */
    protected void populateRoleChoiceBox() {
        /*ObservableList<String> rollen = FXCollections.observableArrayList();*/
        RoleDAO rdao = RoleDAO.getInstance();
        ArrayList<String> roles = rdao.getRoles();
        for ( String role : roles){
            roleChoiceBox.getItems().add(role);
        }
    }

    protected String vertaal(String role) {
        String rol = "";
        switch (role) {
            case "Teacher":
                rol = "Docent";
                break;
            case "Administrator":
                rol = "Administrator";
                break;
            case "SystemAdministrator":
                rol = "Technisch beheerder";
                break;
            case "Student":
                rol = "Student";
                break;
            case "Coordinator":
                rol = "Coördinator";
                break;
        }
        return rol;
    }
    protected String translate(String rol) {
        String role = "";
        switch (rol) {
            case "Docent":
                role = "Teacher";
                break;
            case "Administrator":
                role = "Administrator";
                break;
            case "Technisch beheerder":
                role = "SystemAdministrator";
                break;
            case "Student":
                role = "Student";
                break;
            case "Coördinator":
                role = "Coordinator";
                break;
        }
        return role;
    }
}
