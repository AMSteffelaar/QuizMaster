package model.Database;

import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDAO extends AbstractDAO{
    private static GroupDAO gdao;

    public GroupDAO(DBaccess db) {
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

    public static GroupDAO getInstance(){
        if(gdao==null){
            gdao = new GroupDAO(Main.getInstance());
            return gdao;
        }
        else {
            return gdao;
        }
    }
}
