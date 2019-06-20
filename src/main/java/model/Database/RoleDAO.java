package model.Database;

import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Helper DAO om makkelijk even alle beschikbare rollen op te halen uit de DB
 * zodat deze gemakkelijk als list in bijv. menubuttons in verschillende controllers kunnen worden toegepast
 */
public class RoleDAO extends AbstractDAO {

    private static RoleDAO rdao;

    public RoleDAO(DBaccess db) {
        super(db);
    }
    

    public static RoleDAO getInstance() {
        if (rdao == null) {
            rdao = new RoleDAO(Main.getInstance());
            return rdao;
        } else {
            return rdao;
        }
    }

    public ArrayList<String> getRoles() {
        String sql = "Select * from role";
        ArrayList<String> results = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                String role = rs.getString("roleName");
                results.add(role);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }
}
