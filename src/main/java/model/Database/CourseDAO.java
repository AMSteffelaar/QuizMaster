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

    public CourseDAO (DBaccess dBaccess) {
        super(dBaccess);
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
                String udao_name = udao.getUserNameById(courseCoordinator);
                String udao_password = udao.getUserPasswordById(courseCoordinator);
                //maak een user die een coordinator is
                User user = new Coordinator(udao_name,udao_password);
                result = new Course(courseId, courseName, user);
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }

    //deze methode schrijft een cursus weg naar de db
    // doing
   /* public Course storeCourse(Course course) {
        String sql = "insert into Course (coordinator_idUser, name)"
                + " values(?,?)";
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            //Arnout: hier de userId ophalen met behulp van de naam en het password
            ps.setInt(1, course.getCoordinator()
            ps.setString(2, order.getOrderDate());
            int bestellingId = executeInsertPreparedStatement(ps);//dit levert een nw record in wb, incl de auto-key
            // en levert het bestelnr uit wb terug
            order.setOrderId(bestellingId);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        for (Orderline o : order.getOrderLines()) {
            System.out.println("start nu een storeOrderLine.");
            orderLineDAO.storeOrderLine(o);
        }
        return order;
    }*/

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
                User user = new Coordinator(udao_name,udao_password);
                course = new Course(name, user);
                course.setIdCourse(course_id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return course;
    }

    public static CourseDAO getInstance(){
        if(cdao==null){
            cdao = new CourseDAO(Main.getInstance());
            return cdao;
        }
        else {
            return cdao;
        }
    }
}
