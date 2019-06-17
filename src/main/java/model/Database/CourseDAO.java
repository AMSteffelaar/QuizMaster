package model.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO extends AbstractDAO {

    private static CourseDAO courseDAO;

    public CourseDAO (DBaccess dBaccess) {
        super(dBaccess);
    }

    //deze methode levert alle bestaande cursussen - to do
    public int[] getIds() {
        String sql = "Select * from course";
        int[] results = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                int courseId = rs.getInt("idCourse");
                String courseName = rs.getString("name");
                String courseCoordinator = rs.getString("coordinator_idUser");
                int result = -1;
                //result = new Course(); de klass is door een collega gemaakt, deze ophalen uit de remote
                //results.add(result); add werkt alleen als het een arrayList is
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }


    //deze methode schrijft een cursus weg naar de db - to do - kijk bij bestellingen


    //deze methode levert het unieke Id van de cursus als er een is.
    public int getCourseIdByName(String name) {
        String sql = "Select * from course where name = ?";
        int id = 0;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()) {
                id = rs.getInt("idCourse");

            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return id;
    }
}
