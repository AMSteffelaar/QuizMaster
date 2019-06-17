package model.entity.User;

public class Administrator extends User {

    private static final String ROLE = "Administrator";

    public Administrator(String name, String password){
        super(name, password);
        super.role = ROLE;
    }
}

