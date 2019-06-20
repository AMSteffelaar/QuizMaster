package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Database.CourseDAO;
import model.Database.DBaccess;
import model.Database.GroupDAO;
import model.Database.UserDAO;
import model.entity.Course;
import model.entity.Group;
import model.entity.User.*;
import java.util.ArrayList;

public class Main extends Application {

    private static SceneManager sceneManager = null;
    private static Stage primaryStage = null;
    private static DBaccess db;


    public static SceneManager getSceneManager() {
        if (sceneManager == null) {
            sceneManager = new SceneManager(primaryStage);
        }
        return sceneManager;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Make IT Work - Project 1");
        getSceneManager().setWindowTool();
        primaryStage.show();
    }

    public static void main(String[] args) {
        DBaccess dbAccess = getInstance();
        dbAccess.openConnection();
        GroupDAO gdao = GroupDAO.getInstance();
        ArrayList<Group> groups = gdao.getGroups();
        for (Group g: groups) {
            System.out.println(g.toString());
        }
        launch(args);
        dbAccess.closeConnection();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static DBaccess getInstance() {
        if (db == null) {
            db = new DBaccess();
            return db;
        } else {
            return db;
        }

    }

}


