package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Database.DBaccess;
import model.entity.User.*;

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

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        DBaccess dbAccess = getInstance();
        dbAccess.openConnection();
        Student student1 = new Student("Huub", "huub");
        Coordinator coordinator1 = new Coordinator("Danielle", "danielle");
        SystemAdministrator systemAdministrator1 = new SystemAdministrator("Arnout", "arnout");
        Teacher teacher1 = new Teacher("Gerke", "gerke");
        Administrator administrator1 = new Administrator("Baukje", "baukje");
        launch(args);
        dbAccess.closeConnection();
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

