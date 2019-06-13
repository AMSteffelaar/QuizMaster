package model.entity.User;

public class User {

    private int id;
    private String naam;
    private String password;
    private String rol;

    public User(String naam, String password, String rol) {
        this.naam = naam;
        this.password = password;
        this.rol = rol;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
