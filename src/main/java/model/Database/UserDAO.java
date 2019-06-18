package model.Database;

import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {
    private static UserDAO udao;

    public UserDAO(DBaccess db) {
        super(db);
    }

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


