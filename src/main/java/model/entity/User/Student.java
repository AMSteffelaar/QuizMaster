package model.entity.User;


public class Student extends User {

    private static final String ROLE = "Student";
    public static final String[] SCREENS_STUDENT = new String[]{"SelectQuizForStudent.fxml", "FillOutQuiz.fxml",
            "StudentFeedback.fxml", "StudentSignInOut.fxml"};
    public static final String[] TASKS_STUDENT = new String[]{"Kies quiz", "Maak een quiz", "Zie quizresultaten",
            "Schrijf in/uit"};

    public Student(String name, String password){
        super(name, password);
        super.setRole(ROLE);
        //super.screens = new String[]{"StudentFeedback.fxml", "FillOutQuiz.fxml", "SelectQuizForStudent.fxml", "StudentSigninOut"};
    }

    public void subscribe(){

    }

    public void unsubscribe(){

    }

    @Override
    public String toString() {
        return "student";
    }
}