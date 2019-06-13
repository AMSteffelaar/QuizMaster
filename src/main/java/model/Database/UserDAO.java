package model.Database;

import model.entity.User.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {

    public UserDAO(DBaccess db) {
        super(db);
    }

    public int getUserIdByNamePassword(String naam, String password) {
        String sql = "Select * from gebruiker where naam = ? and paswoord = ?";
        int id = 0;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, naam);
            ps.setString(2, password);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()) {
                id = rs.getInt("idGebruiker");

            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return id;
    }

    public User getUserByID(int id) {
        String sql = "Select * from klant where klantnr = ?";
        User result = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()) {
                String naam = rs.getString("naam");
                String password = rs.getString("paswoord");
                String rol = rs.getString("rol_rol_naam");
                result = new User(naam, password, rol);
                result.setId(id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

//    public static UserDAO getInstance(){
//        if(udao==null){
//            udao = new UserDAO(db);
//            return udao;
//        }
//        else {
//            return udao;
//        }
//    }
}


