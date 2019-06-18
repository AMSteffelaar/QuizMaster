package controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Database.UserDAO;
import model.entity.User.User;
import view.Main;



public class ChangeUserController {

  @FXML
  private TextField nameField;

  @FXML
  private TextField passwordField;

  public void setup(User user) {
  }

  public void doMenu(ActionEvent event) {
    Main.getSceneManager().showLoginScene();
  }

  public void changeRole(String name) {

  }

public void doChangeUser(ActionEvent event){
    String name = nameField.getText();
    String passwoord = passwordField.getText();

    UserDAO udao = UserDAO.getInstance();
    }

    //update sql
  }
  }
}
