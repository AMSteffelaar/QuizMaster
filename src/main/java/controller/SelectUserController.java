package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Database.UserDAO;
import model.entity.Course;
import model.entity.User.User;
import view.SceneManager;

import java.util.ArrayList;

public class SelectUserController {

  private UserDAO udao = UserDAO.getInstance();
  private SceneManager manager = SceneManager.getSceneManager();


  @FXML
  private ListView userList = new ListView();

  @FXML
  /**
   * wordt gestart bij het aanroepen van de view via de Scenemanager
   */
  public void setup() {
    populateScreen();
  }

  private void populateScreen(){
    ObservableList<String> user = FXCollections.observableArrayList();
    ArrayList<User> users ;
    users = udao.getUsers();
    for (User u : users) {
      user.add(u.getName());
    }
    userList.setItems(user);
  }

  /**
   * gaat terug naar WelcomeController
   * @param event
   */
  public void doMenu(ActionEvent event) {
    manager.showWelcomeScene();
  }

  public void doChangeUser(ActionEvent event){
    User user = udao.getUserByName((String)userList.getSelectionModel().getSelectedItem());
    manager.showChangeUserScene(user);
  }

  public void doDeleteUser(ActionEvent event){
    User user = udao.getUserByName((String)userList.getSelectionModel().getSelectedItem());
    udao.deleteUser(user);
    manager.showSelectUserScene();
  }
}
