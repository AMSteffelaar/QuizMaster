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
import view.Main;

import java.util.ArrayList;

public class NewUserController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private MenuButton roleMenuButton = new MenuButton();

    public void setup() {
        //JK
        //haalt alle rollen uit DB tabel rollen
        ArrayList<String> roles = RoleDAO.getInstance().getRoles();
        //maakt een list die kan worden gebruikt om in de MenuButton te stoppen
        ObservableList<String> rollen = FXCollections.observableArrayList(roles);
        //vult op basis van de hoeveelheid rollen de menu items en geeft ze functionaliteit dat de gekozen waarde wordt behouden
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

    public void doMenu(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }

    public void doCreateUser(ActionEvent event) {
        //JK
        String name = nameField.getText();
        String password = passwordField.getText();
        String role = roleMenuButton.getText();
        UserDAO.getInstance().storeUser(name, password, role);
        Main.getSceneManager().showSelectUserScene();
    }
}
