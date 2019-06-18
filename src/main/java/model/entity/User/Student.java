package model.entity.User;


public class Student extends User {

    private static final String ROLE = "Student";

    public Student(String name, String password){
        super(name, password);
        super.setRole(ROLE);
    }

    public void subscribe(){

    }

    public void unsubscribe(){

    }
}