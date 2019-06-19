package model.Database;

import model.entity.User.*;
import view.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {
    private static UserDAO udao;

    public UserDAO(DBaccess db) {
        super(db);
    }

    public int getUserIdByNamePassword(String name, String password) {
        String sql = "Select * from user where name = ? and password = ?";
        int id = 0;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()) {
                id = rs.getInt("idUser");

            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return id;
    }

    //Arnout: een get user_name by user_id tbv CourseDAO
    public String getUserNameById(int id) {
        String sql = "Select * from user where idUser = ?";
        String name = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
                return name;
            }

    /**
     * Haalt een user uit de database met een bepaald id.
     * @param id het id waarvoor je de user wil ophalen.
     * @return de User.
     */
    public User getUserById(int id) {
        String sql = "Select * from user where idUser = ?";
        User user = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String role = rs.getString("role_roleName");
                user = createUser(name, password, role);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return user;
    }

    /**
     * Maakt een user van het juiste type, afhankelijk van de rol.
     * @param name naam van de user
     * @param password password van de user
     * @param role rol van de user
     * @return een User van het juiste type.
     */
    private User createUser(String name, String password, String role) {
        User user = null;
        switch (role) {
            case "Teacher":
                user = new Teacher(name, password);
                break;
            case "Administrator":
                user = new Administrator(name, password);
                break;
            case "SystemAdministrator":
                user = new SystemAdministrator(name, password);
                break;
            case "Student":
                user = new Student(name, password);
                break;
            case "Coordinator":
                user = new Coordinator(name, password);
                break;
        }
        return user;
    }
    // updaten van de gebruiker door de
        private User changeUser( String name, String password) {
            String sql = "UPDATE user SET name = ?, password = ?, role =?";
            try {
                PreparedStatement ps = getStatement(sql);
                ps.setString(1, changeUser(name, password).getName());
                ps.setString(2, changeUser(name, password).getPassword());
                ps.setString(3, changeUser(name, password).getRole());
                executeManipulatePreparedStatement(ps); // hierdoor krijg je niks terug en wordt het gewoon aagepast.
            } catch (SQLException e) {
                System.out.println("SQL error: " + e.getMessage());
            }
        }


    public static UserDAO getInstance(){
        if(udao==null){
            udao = new UserDAO(Main.getInstance());
            return udao;
        }
        else {
            return udao;
        }
    }

    //Arnout tbv CourseDAO
    public String getUserPasswordById(int id) {
        String sql = "Select * from user where idUser = ?";
        String password = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                password = rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return password;
    }
}


