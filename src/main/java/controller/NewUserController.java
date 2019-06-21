package controller;

import javafx.event.ActionEvent;

public class NewUserController extends UpdateUserController {

    /**
     * wordt gestart bij het aanroepen van de view via de Scenemanager
     */
    public void setup() {
        populateRoleChoiceBox();
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
     * Haalt gevulde tekstvelden op en keuze van rol en schrijft nieuw gebruiker weg in de MySQL database.
     * Aansluitend keer je terug naar scherm SelectUser.
     *
     * @param event noodzakelijk ivm JavaFX, verder niet in gebruik
     */
    public void doCreateUser(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        String role = roleMenuButton.getText();
        if (udao.getUserByName(name) == null) {
            udao.storeUser(name, password, role);
            manager.showSelectUserScene();
        } else {
            nameField.setText("Al in gebruik");
        }
    }


}
