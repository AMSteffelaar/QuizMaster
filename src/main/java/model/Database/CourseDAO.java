package model.Database;

import model.entity.Course;
import model.entity.User.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO extends AbstractDAO {

    private static CourseDAO courseDAO;

    public CourseDAO (DBaccess dBaccess) {
        super(dBaccess);
    }

    //deze methode levert alle bestaande cursussen
    //deze methode levert: een arrayList van de bestaande courses
    // - done - nog testen
    public ArrayList<Course> getCourses() {
        String sql = "Select * from course";
        ArrayList<Course> results = null;
        Course result;
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                int courseId = rs.getInt("idCourse");
                int courseCoordinator = rs.getInt("coordinator_idUser");
                String courseName = rs.getString("name");
                UserDAO udao = UserDAO.getInstance();
                String udao_name = udao.getUserNameById(courseCoordinator);
                String udao_password = udao.getUserPasswordById(courseCoordinator);
                User user = new User(udao_name,udao_password);
                result = new Course(courseId, courseName, user);
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }

    //deze methode schrijft een cursus weg naar de db - to do - kijk bij bestellingen


    //deze methode update een cursus - to do



    //deze methode delete een cursus - to do




    //deze methode levert een unieke cursus van de lijst met cursus namen als deze voorkomt.
    public Course getCourseByName(String name) {
        String sql = "Select * from course where name = ?";
        int course_id;
        int coordinator_id;
        Course course = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                course_id = rs.getInt("idCourse");
                coordinator_id = rs.getInt("coordinator_idUser");
                //mbv getUserNameByUserId in UserDAO de user naam ophalen
                UserDAO udao = UserDAO.getInstance();
                String udao_name = udao.getUserNameById(coordinator_id);
                String udao_password = udao.getUserPasswordById(coordinator_id);
                User user = new User(udao_name,udao_password);
                course = new Course(name, user);
                course.setIdCourse(course_id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return course;
    }
}
