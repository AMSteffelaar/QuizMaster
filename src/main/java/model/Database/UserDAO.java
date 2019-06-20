package model.Database;

import model.entity.Course;
import model.entity.User.*;
import view.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO {
    private static UserDAO udao;

    public UserDAO(DBaccess db) {
        super(db);
    }

    /**
     * Singleton constructie, waardoor er maar 1 instantie van de UserDAO bestaat.
     * Zie het als een getter voor een instantie.
     * Dit zodat er altijd één stabiele koppeling is en blijft met de MySQL DB.
     * @return Een stabiele koppeling met de MySQL db.
     */
    public static UserDAO getInstance() {
        if (udao == null) {
            udao = new UserDAO(Main.getInstance());
            return udao;
        } else {
            return udao;
        }
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
     *
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
     *
     * @param name     naam van de user
     * @param password password van de user
     * @param role     rol van de user
     * @return een User van het juiste type.
     */
    public User createUser(String name, String password, String role) {
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
    

    // updaten van de gebruiker door de SystemAdministrator
    public void changeUser(User user, int id) {
       /* User user = createUser(nameUser, password, role);*/
        String sql = "update user SET role_roleName = ?, name = ?, password = ? WHERE idUser = ?";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, user.getRole());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, id);
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
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
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return password;
    }

    /**
     * Slaat nieuw gemaakte user die is ingevoerd in de NewUserController op in de MySQL DB
     * JK
     * @param naam     naam van de user
     * @param password password van de user
     * @param role     rol van de user
     */
    public void storeUser(String naam, String password, String role) {
        User user = createUser(naam, password, role);
        String sql = "INSERT INTO `quizmaster`.`user` (`role_roleName`, `name`, `password`) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, user.getRole());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    //Arnout: tbv SelectUserController
    public ArrayList<User> getUsers() {
        String sql = "Select * from user";
        ArrayList<User> results = null;
        User result = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                String role = rs.getString("role_roleName");
                String nameUser = rs.getString("name");
                String password = rs.getString("password");
                UserDAO udao = UserDAO.getInstance();
                //maak een user afhankelijk van de role
                switch (role) {
                    case "Student" : result = new Student(nameUser,password);
                    case "Teacher" : result = new Teacher(nameUser,password);
                    case "Coordinator" : result = new Coordinator(nameUser,password);
                    case "Administrator" : result = new Administrator(nameUser,password);
                    case "SystemAdministrator" : result = new SystemAdministrator(nameUser,password);
                    //een default is niet nodig
                }
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }


    //Arnout tbv SelectUserController, doChangeUser
    public User getUserByName(String name) {
        String sql = "Select * from user where name = ?";
        String password = null;
        String role = null;
        User user = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                password = rs.getString("password");
                role = rs.getString("role_roleName");
                UserDAO udao = UserDAO.getInstance();
                //maak een user afhankelijk van de role
                switch (role) {
                    case "Student" : user = new Student(name,password);
                    case "Teacher" : user = new Teacher(name,password);
                    case "Coordinator" : user = new Coordinator(name,password);
                    case "Administrator" : user = new Administrator(name,password);
                    case "SystemAdministrator" : user = new SystemAdministrator(name,password);
                        //een default is niet nodig
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return user;
    }

    //deze methode delete een user - doing
    public void deleteUser(User user) {
        String sql = "delete FROM quizmaster.user where IdUser = ?;";
        UserDAO udao = UserDAO.getInstance();
        //getUserId wordt gemaakt door Joost - voor nu even de constante 10
        int userId = 11;
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setInt(1, userId);
            udao.executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}


