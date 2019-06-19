package model.entity.User;

public class SystemAdministrator extends User {

    private static final String ROLE = "SystemAdministrator";

    public SystemAdministrator(String name, String password) {
        super(name, password);
        super.setRole(ROLE);
        super.screens = new String[]{"ChanceUser.fxlm", "NewUser.fxml", "SelectUser.fxml"};
    }
}
