package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.entity.Quiz;
import model.entity.Session;
import model.entity.User.*;
import view.Main;
import view.SceneManager;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class WelcomeController {

    private SceneManager manager = SceneManager.getSceneManager();

    @FXML
    private MenuButton taskMenuButton;

    @FXML
    private Label welcomeLabel;


    /**
     * Geeft een verschillende welkomstboodschap, afhankelijk van de lokale tijd.
     * Verwelkomt de currentUser met zijn naam en functie.
     */
    @FXML
    public void setup() {
        LocalTime timeNow = LocalTime.now();
        if (timeNow.isAfter(LocalTime.of(7, 0, 0)) &&
                timeNow.isBefore(LocalTime.of(12, 0, 0))) {
            welcomeLabel.setText("Goedemorgen " + Session.getInstance().getCurrentUser().getName() +
                    "! U bent ingelogd als " + Session.getInstance().getCurrentUser().toString() + ".");
        } else if (timeNow.isBefore(LocalTime.of(18, 0, 0))) {
            welcomeLabel.setText("Goedemiddag " + Session.getInstance().getCurrentUser().getName() +
                    "! U bent ingelogd als " + Session.getInstance().getCurrentUser().toString() + ".");
        } else if (timeNow.isBefore(LocalTime.of(23, 59, 59))) {
            welcomeLabel.setText("Goedenavond " + Session.getInstance().getCurrentUser().getName() +
                    "! U bent ingelogd als " + Session.getInstance().getCurrentUser().toString() + ".");
        } else {
            welcomeLabel.setText("Goedenacht " + Session.getInstance().getCurrentUser().getName() +
                    "! U bent ingelogd als " + Session.getInstance().getCurrentUser().toString() + ".");
        }
        populateTaskMenu(Session.getInstance().getCurrentUser().getRole());
    }


    /**
     * Maakt een menu met taken, afhankelijk van de rol van wie er heeft ingelogd.
     * Springt naar het scherm waar voor wordt gekozen.
     *
     * @param roleCurrentUser De rol van de huidig ingelogde User.
     */
    public void populateTaskMenu(String roleCurrentUser) {
        ArrayList<String> screensUser = new ArrayList<>();
        ArrayList<String> tasksUser = new ArrayList<>();
        //Vul de ArrayLists met de schermen en taken die bij de rol van de User horen.
        switch (roleCurrentUser) {
            case "Administrator":
                screensUser.addAll(Arrays.asList(Administrator.SCREENS_ADMIN));
                tasksUser.addAll(Arrays.asList(Administrator.TASKS_ADMIN));
                break;
            case "SystemAdministrator":
                screensUser.addAll(Arrays.asList(SystemAdministrator.SCREENS_SYSTEM_ADMIN));
                tasksUser.addAll(Arrays.asList(SystemAdministrator.TASKS_SYSTEM_ADMIN));
                break;
            case "Student":
                screensUser.addAll(Arrays.asList(Student.SCREENS_STUDENT));
                tasksUser.addAll(Arrays.asList(Student.TASKS_STUDENT));
                break;
            case "Coordinator":
                screensUser.addAll(Arrays.asList(Coordinator.SCREENS_COORDINATOR));
                tasksUser.addAll(Arrays.asList(Coordinator.TASKS_COORDINATOR));
                break;
        }
        //Maak een bepaald aantal MenuItems, passend bij hoeveel taken die User heeft.
        for (int i = 0; i < tasksUser.size(); i++) {
            MenuItem item = new MenuItem();
            String task = tasksUser.get(i);
            item.setText(task);
            taskMenuButton.getItems().add(item);
            String screenName = screensUser.get(i);

            //Spring naar goede scherm.
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    switch (screenName) {
                        case "newUser.fxml":
                            manager.showNewUserScene();
                            break;
                        case "selectUser.fxml":
                            manager.showSelectUserScene();
                            break;
                        case "newGroup.fxml":
                            manager.showNewGroupScene();
                            break;
                        case "newCourse.fxml":
                            manager.showNewCourseScene();
                            break;
                        case "manageCourses.fxml":
                            manager.showManageCoursesScene();
                            break;
                        case "manageGroups.fxml":
                            manager.showManageGroupsScene();
                            break;
                        case "selectQuizForStudent.fxml":
                            manager.showSelectQuizForStudent();
                            break;
                        case "coordinatorDashboard.fxml":
                            manager.showCoordinatorDashboard();
                            break;
                        /*case "fillOutQuiz.fxml":
                            manager.showFillOutQuiz();
                            break;*/
                        case "studentSignInOut.fxml":
                            manager.showStudentSignInOutScene();
                            break;
                    }
                }
            });
        }
    }


    public void doLogout(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }
}
