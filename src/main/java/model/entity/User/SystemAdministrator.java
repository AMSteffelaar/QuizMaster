package model.entity.User;

public class SystemAdministrator extends User {

    private static final String ROLE = "SystemAdministrator";
    public static final String[] SCREENS_SYSTEM_ADMIN = new String[]{"changeUser.fxml",
            "newUser.fxml", "selectUser.fxml"};
    public static final String[] TASKS_SYSTEM_ADMIN = new String[]{"Verander gebruiker",
            "Voeg nieuwe gebruiker toe", "Selecteer gebruiker"};

    public SystemAdministrator(String name, String password) {
        super(name, password);
        super.setRole(ROLE);
    }

    @Override
    public String toString() {
        return "technisch beheerder";
    }
}
