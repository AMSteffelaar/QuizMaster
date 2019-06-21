package model.entity.User;

public class Coordinator extends Teacher {

        private static final String ROLE = "Coordinator";
        public static final String[] SCREENS_COORDINATOR = new String[]{"coordinatorDashboard.fxml",};
        public static final String[] TASKS_COORDINATOR = new String[]{"Ga naar Coördinator Dashboard"};


        public Coordinator(String name, String password){
            super(name, password);
            super.setRole(ROLE);
        }

    @Override
    public String toString() {
        return "coördinator ";
    }
}

