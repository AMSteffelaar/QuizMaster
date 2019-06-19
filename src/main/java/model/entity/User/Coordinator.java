package model.entity.User;

public class Coordinator extends Teacher {

    private static final String ROLE = "Coordinator";

    public Coordinator(String name, String password) {
        super(name, password);
        super.role = ROLE;
        super.screens = new String[]{"NewQuiz.fxml", "NewQuestion.fxml"};
    }

}

