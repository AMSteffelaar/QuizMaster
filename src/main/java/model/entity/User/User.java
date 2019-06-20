package model.entity.User;


public abstract class User {

    private int id;
    private String name;
    private String password;
    private String role;

    /**
     * Constructor die liefst alleen wordt aangeroepen wanneer een user al in de DB aanwezig is. geeft mogelijk problemen met nieuw user
     * @param name
     * @param password
     */
    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    //Arnout: constructor tbv CourseDAO
    public User(String name) {
        super();
        this.name = name;
    }

    public String toString() {
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

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }
    }


