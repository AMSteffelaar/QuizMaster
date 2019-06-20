package model.entity.User;

public class Coordinator extends Teacher {

        private static final String ROLE = "Coordinator";

        public Coordinator(String name, String password){
            super(name, password);
            super.setRole(ROLE);
            super.screens = new String[]{"NewQuiz.fxml", "NewQuestion.fxml"};
        }

    @Override
    public String toString() {
        return super.toString() + " in de rol van Coordinator.";
    }

}

