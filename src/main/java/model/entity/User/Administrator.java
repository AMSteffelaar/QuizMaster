package model.entity.User;

public class Administrator extends User {

    private static final String ROLE = "Administrator";
    public static final String[] SCREENS_ADMIN = new String[]{"newGroup.fxml", "newCourse.fxml",
            "manageGroups.fxml", "manageCourses.fxml", "changeGroup.fxml", "changeCourse.fxml"};
    public static final String[] TASKS_ADMIN = new String[]{"Maak nieuwe groep", "Maak nieuwe cursus",
            "Beheer groepen", "Beheer cursussen", "Verander groep", "Verander cursus"};

    public Administrator(String name, String password) {
        super(name, password);
        super.setRole(ROLE);
    }

    @Override
    public String toString() {
        return "administrator";
    }
}

