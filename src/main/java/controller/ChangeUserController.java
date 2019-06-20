package controller;

import javafx.event.ActionEvent;
import model.entity.User.User;


public class ChangeUserController extends UpdateUserController {
    private int idUser;

    public void setup(User user) {
        idUser = user.getId();
        nameField.setText(user.getName());
        passwordField.setText(user.getPassword());
        roleMenuButton.setText(vertaal(user.getRole()));
        populateScreen();
    }

    public void doMenu(ActionEvent event) {
        manager.showWelcomeScene();
    }


    public void doChangeUser(ActionEvent event) {
        String name = nameField.getText();
        if (!nameField.hasProperties()){
            nameField.setText("Dit veld mag niet leeg zijn");
        }
        String password = passwordField.getText();
        if (!passwordField.hasProperties()){
            passwordField.setText("Dit veld mag niet leeg zijn");
        }
        String role = translate(roleMenuButton.getText());
        User user = udao.createUser(name, password, role);
        if (udao.getUserByName(name) == null  && nameField.hasProperties() && passwordField.hasProperties()) {
            udao.changeUser(user, idUser);
            manager.showSelectUserScene();
        } else {
            nameField.setText("Al in gebruik");
        }
    }
}