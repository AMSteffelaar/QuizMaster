package model.entity.User;

public class Administrator extends User {

    private static final String ROLE = "Administrator";
    public static final String[] SCREENS_ADMIN = new String[]{"manageGroups.fxml", "manageCourses.fxml"};
    public static final String[] TASKS_ADMIN = new String[]{"Beheer groepen", "Beheer cursussen"};

    public Administrator(String name, String password) {
        super(name, password);
        super.setRole(ROLE);
    }

    @Override
    public String toString() {
        return "administrator";
    }
}

