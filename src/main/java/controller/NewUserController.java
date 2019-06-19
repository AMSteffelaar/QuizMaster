package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class NewUserController {

  @FXML
  private TextField nameField;

  @FXML
  private TextField passwordField;

  @FXML
  private MenuButton roleMenuButton = new MenuButton();

  public void setup() {}

  public void doMenu(ActionEvent event){}

  public void doCreateUser(ActionEvent event){}
}
