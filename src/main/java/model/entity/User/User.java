package model.entity.User;


public abstract class User {

    private String name;
    private String password;
    private String role;

    protected String[] screens;

    public User(String name, String password){
        super();
        this.name = name;
        this.password = password;
        this.role = role;
    }

    //Arnout: constructor tbv CourseDAO
    public User(String name) {
        super();
        this.name = name;
    }

    public String toString(){
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getScreens() {
        return screens;
    }
}
