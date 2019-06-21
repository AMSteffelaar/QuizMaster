package model.Database;

import model.entity.Course;
import model.entity.Group;
import model.entity.User.Coordinator;
import model.entity.User.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDAO extends AbstractDAO{
    private static GroupDAO gdao;

    public GroupDAO(DBaccess db) {
        super(db);
    }

    /*public int getUserIdByNamePassword(String name, String password) {
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
    }*/



    //deze methode levert alle bestaande groups
    //deze methode levert: een arrayList van de bestaande groups
    public ArrayList<Group> getGroups() {
        String sql = "SELECT * FROM quizmaster.`group`;";
        ArrayList<Group> results = null;
        Group result;
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                int groupId = rs.getInt("idGroup");
                int course_idCourse = rs.getInt("course_idCourse");
                int teacher_idUser = rs.getInt("teacher_idUser");
                String courseName = rs.getString("name");
                UserDAO udao = UserDAO.getInstance();
                User user = udao.getUserById(teacher_idUser);
                CourseDAO cdao = CourseDAO.getInstance();
                Course course = cdao.getCourseById(course_idCourse);
                result = new Group(courseName, user, course);
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }

    public void storeGroup(int courseid, int docentid, String naam) {
        String sql = "INSERT INTO `quizmaster`.`group` (`course_idCourse`, `teacher_idUser`, `name`) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setInt(1, courseid);
            ps.setInt(2, docentid);
            ps.setString(3, naam);
            executeInsertPreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }





    public void deleteGroup (Group group) {
        String sql = "delete FROM quizmaster.group where IdGroup = ?;";
        GroupDAO gdao = GroupDAO.getInstance();
        int idGroup = group.getIdGroup();
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, idGroup);
            gdao.executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
    public Group getGroupById (int id) {
            String sql = "Select * from group where idGroup = ?";
            Group group = null;
            try {
                PreparedStatement ps = getStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = executeSelectPreparedStatement(ps);
                while (rs.next()) {
                    String name = rs.getString("name");
                    int teacher_idUser = rs.getInt ("teacher_idUser");
                    UserDAO udao = UserDAO.getInstance();
                    User teacher = udao.getUserById(teacher_idUser);
                    CourseDAO cdao = CourseDAO.getInstance();
                    Course courses = cdao.getCourseById(id);
                    group = new Group (name, teacher, courses);
                }
            } catch (SQLException e){
                System.out.println("SQL error: " + e.getMessage());
        }
            return group;

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
