package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.Database.RoleDAO;
import model.Database.UserDAO;
import view.SceneManager;

import java.util.ArrayList;

public class NewUserController {
    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private MenuButton roleMenuButton = new MenuButton();

    /**
     * wordt gestart bij het aanroepen van de view via de Scenemanager
     */
    public void setup() {
        populateScreen();
    }

    /**
     * Keert terug naar WelcomeController
     *
     * @param event noodzakelijk ivm JavaFX, verder niet in gebruik
     */
    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }

    /**
     * Joost Kager
     * Haalt gevulde tekstvelden op en keuze van rol en schrijft nieuw gebruiker weg in de MySQL database.
     * Aansluitend keer je terug naar scherm SelectUser.
     *
     * @param event noodzakelijk ivm JavaFX, verder niet in gebruik
     */
    public void doCreateUser(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        String role = roleMenuButton.getText();
        if (UserDAO.getInstance().getUserByName(name) == null) {
            UserDAO.getInstance().storeUser(name, password, role);
            manager.showSelectUserScene();
        } else {nameField.setText("Al in gebruik");
        }
    }

    /**
     * Haalt rollen op uit table Role in MySQL DB en vult de Menubutton 'kies Rol'
     * geeft tevens de functionaliteit dat de gekozen rol zichtbaar blijft en kan worden uitgelezen
     * als nieuwe gebruiker wordt aangemaakt.
     */
    private void populateScreen() {
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
            roleMenuButton.getItems().add(item);
        }
    }
}
