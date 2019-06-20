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

    public User getUserByName(String username) {
        String sql = "Select * from user where name = ?";
        User user = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, username);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                int id = rs.getInt("idUser");
                user = getUserById(id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return user;
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
                user.setId(id);
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

    /**
     * Slaat nieuw gemaakte user die is ingevoerd in de NewUserController op in de MySQL DB
     * @param naam     naam van de user
     * @param password password van de user
     * @param role     rol van de user
     */
    public void storeUser(String naam, String password, String role) {
        User user = createUser(naam, password, role);
        String sql = "INSERT INTO `quizmaster`.`user` (`role_roleName`, `name`, `password`) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setString(1, user.getRole());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            int id = executeInsertPreparedStatement(ps);
            user.setId(id);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    //Arnout: tbv SelectUserController
    public ArrayList<User> getUsers() {
        String sql = "Select * from user";
        ArrayList<User> results = null;
        User result;
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                String role = rs.getString("role_roleName");
                String nameUser = rs.getString("name");
                String password = rs.getString("password");
                result = createUser(nameUser, password, role);
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }


    //Arnout tbv SelectUserController, doChangeUser
//    public User getUserByName(String name) {
//        String sql = "Select * from user where name = ?";
//        String password = null;
//        String role = null;
//        User user = null;
//        try {
//            PreparedStatement ps = getStatement(sql);
//            ps.setString(1, name);
//            ResultSet rs = executeSelectPreparedStatement(ps);
//            while (rs.next()) {
//                password = rs.getString("password");
//                role = rs.getString("role_roleName");
//                UserDAO udao = UserDAO.getInstance();
//                //maak een user afhankelijk van de role
//                switch (role) {
//                    case "Student" : user = new Student(name,password);
//                    case "Teacher" : user = new Teacher(name,password);
//                    case "Coordinator" : user = new Coordinator(name,password);
//                    case "Administrator" : user = new Administrator(name,password);
//                    case "SystemAdministrator" : user = new SystemAdministrator(name,password);
//                        //een default is niet nodig
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("SQL error " + e.getMessage());
//        }
//        return user;
//    }

    //deze methode delete een user
    public void deleteUser(User user) {
        String sql = "delete FROM quizmaster.user where IdUser = ?;";
        UserDAO udao = UserDAO.getInstance();
        int userId = user.getId();
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setInt(1, userId);
            udao.executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}


