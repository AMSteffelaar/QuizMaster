package model.entity.User;

public class Administrator extends User {

    private static final String ROLE = "Administrator";


    public Administrator(String name, String password) {
        super(name, password);
        super.setRole(ROLE);
        super.screens= new String[]{"NewGroup.fxml", "NewCourse.fxml", "ManageGroups.fxml", "ManageCourses", "ChangeGroup.fxml", "ChangeCourse.fxml"};

    }
}

