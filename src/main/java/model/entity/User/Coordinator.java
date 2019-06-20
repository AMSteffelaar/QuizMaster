package model.entity.User;

public class Coordinator extends Teacher {

        private static final String ROLE = "Coordinator";
        public static final String[] SCREENS_COORDINATOR = new String[]{"NewQuiz.fxml", "NewQuestion.fxml"};
        public static final String[] TASKS_COORDINATOR = new String[]{"Maak nieuwe quiz voor cursus",
                "Maak nieuwe vraag voor quiz"};


        public Coordinator(String name, String password){
            super(name, password);
            super.setRole(ROLE);
        }

    @Override
    public String toString() {
        return "coördinator " + super.getName();
    }
}

