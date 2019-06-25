package model.Database;

import model.entity.Course;
import model.entity.User.Coordinator;
import model.entity.User.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO extends AbstractDAO {

    private static CourseDAO cdao;

    public static CourseDAO getInstance() {
        if (cdao == null) {
            cdao = new CourseDAO(Main.getInstance());
            return cdao;
        } else {
            return cdao;
        }
    }

    //deze methode levert de course adhva het Id, deze is nodig om
    // een group te maken in
    //de methode getGroups in groupDAO

    public CourseDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    //deze methode levert de course adhva het Id.
    public Course getCourseById(int id) {
        String sql = "Select * from course where idCourse = ?";
        Course course = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String name = rs.getString("name");
                int coordinator_IdUser = rs.getInt("coordinator_idUser");
                UserDAO udao = UserDAO.getInstance();
                User coordinator = udao.getUserById(coordinator_IdUser);
                course = new Course(name, coordinator);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return course;
    }

    //deze methode levert alle bestaande cursussen
    //deze methode levert: een arrayList van de bestaande courses
    public ArrayList<Course> getCourses() {
        String sql = "Select * from course";
        ArrayList<Course> results = null;
        Course result;
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                int courseId = rs.getInt("idCourse");
                int courseCoordinator = rs.getInt("coordinator_idUser");
                String courseName = rs.getString("name");
                UserDAO udao = UserDAO.getInstance();
                User user = udao.getUserById(courseCoordinator);
                result = new Course(courseId, courseName, user);
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }

    //deze methode schrijft een cursus weg naar de db
    public void storeCourse(Course course) {
        String sql = "insert into Course (coordinator_idUser, name)"
                + " values(?,?)";
        UserDAO udao = UserDAO.getInstance();
        int coordinatorId;
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            //Arnout: hier de userId ophalen met behulp van de naam en het password
            coordinatorId = udao.getUserByName(course.getCoordinator().getName()).getId();
            ps.setInt(1, coordinatorId);
            ps.setString(2, course.getName());
            executeInsertPreparedStatement(ps);
            //dit levert een nw record in wb, incl de auto-key en levert het nieuwe id terug
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    //deze methode update van een cursus de cursus naam
    //afstemmen met Inge wat er binnen moet komen in deze methode voor wat betreft de nieuwe coordinator
    public void updateCourse(int courseId, int idcoordinator, String cursusnaam) {
        String sql = "update course set coordinator_idUser = ?, name = ? where idCourse = ?;";
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setInt(1, idcoordinator);
            ps.setString(2, cursusnaam);
            ps.setInt(3, courseId);
            cdao.executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    //methode getCourseIdByCourseUserName deze is nodig voor de testgevallen
    private Integer getCourseIdByUserName(User user, String name) {
        String sql = "Select idCourse from course where coordinator_idUser = ? and name = ?";
        int course_id = -1;
        UserDAO udao = UserDAO.getInstance();
        int userId = udao.getUserByName(user.getName()).getId();
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, name);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                course_id = rs.getInt("idCourse");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return course_id;
    }

    //deze methode delete een cursus
    public void deleteCourse(Course course) {
        String sql = "delete FROM quizmaster.course where Idcourse = ?;";
        CourseDAO cdao = CourseDAO.getInstance();
        int courseId = course.getIdCourse();
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, courseId);
            cdao.executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

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
                UserDAO udao = UserDAO.getInstance();
                Coordinator coordinator = (Coordinator) udao.getUserById(coordinator_id);
                course = new Course(name, coordinator);
                course.setIdCourse(course_id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return course;
    }
}
