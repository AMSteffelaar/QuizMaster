package model.entity.User;

import java.util.ArrayList;

public class Administrator extends User {

    private static final String ROLE = "Administrator";
    public static final String[] SCREENS_ADMIN = new String[]{"NewGroup.fxml", "NewCourse.fxml",
            "ManageGroups.fxml", "ManageCourses.fxml", "ChangeGroup.fxml", "ChangeCourse.fxml"};
    public static final String[] TASKS_ADMIN = new String[]{"Maak nieuwe groep", "Maak nieuwe cursus",
            "Beheer groepen", "Beheer cursussen", "Verander groep", "Verander cursus"};

    public Administrator(String name, String password) {
        super(name, password);
        super.setRole(ROLE);
        //super.screens = new String[]{"NewGroup.fxml", "NewCourse.fxml", "ManageGroups.fxml", "ManageCourses", "ChangeGroup.fxml", "ChangeCourse.fxml"};

    }

    @Override
    public String toString() {
        return "administrator";
    }
}

