package controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    Stage window;
    Button button;
    ComboBox<String> comboBox;

    public void setup(User user) {
    }

    public void doMenu(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }

    public void changeRole(ActionEvent event) throws Exception {
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Student", "Docent", "Coordinator");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(comboBox);

    }

    public void doChangeUser(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        UserDAO udao = UserDAO.getInstance();

        //deze is wel anders
        int iduser = udao.getUserIdByNamePassword(name, password);
        User user = udao.changeUser(name, password);
        if (iduser != 0) {
            SceneManager.getSceneManager().showSelectUserScene();
        } else {
            SceneManager.getSceneManager().showLoginFailedScene();
        }
    }

    public static void main(String[] args) {
    }
}
