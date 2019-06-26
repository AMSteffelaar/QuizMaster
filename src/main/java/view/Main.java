package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Database.CouchDBaccess;
import model.Database.DBaccess;

public class Main extends Application {

    private static SceneManager sceneManager = null;
    private static Stage primaryStage = null;
    private static DBaccess db = DBaccess.getInstance();
    private static CouchDBaccess cdb = CouchDBaccess.getInstance();


    public static SceneManager getSceneManager() {
        if (sceneManager == null) {
            sceneManager = new SceneManager(primaryStage);
        }
        return sceneManager;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        db.openConnection();
        cdb.run();
        launch(args);
        db.closeConnection();
        cdb.close();
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

}


