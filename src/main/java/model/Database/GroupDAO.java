package model.Database;

import model.entity.Course;
import model.entity.Group;
import model.entity.User.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDAO extends AbstractDAO {
    private static GroupDAO gdao;

    public GroupDAO(DBaccess db) {
        super(db);
    }

    public static GroupDAO getInstance() {
        if (gdao == null) {
            gdao = new GroupDAO(db.getInstance());
            return gdao;
        } else {
            return gdao;
        }
    }

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
                result.setIdGroup(groupId);
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

    public void updateGroup(int groupID, int cursusID, int docentId, String naamGroep) {
        String sql = "UPDATE `quizmaster`.`group` SET `course_idCourse` = ?, `teacher_idUser` = ?, `name` = ? WHERE (`idGroup` = ?);";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, cursusID);
            ps.setInt(2, docentId);
            ps.setString(3, naamGroep);
            ps.setInt(4, groupID);
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void deleteGroup(Group group) {
        String sql = "delete FROM quizmaster.group where IdGroup = ?;";
        int idGroup = group.getIdGroup();
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, idGroup);
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public Group getGroupById(int id) {
        String sql = "Select * from Quizmaster.group where idGroup = ?";
        Group group = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String name = rs.getString("name");
                int teacher_idUser = rs.getInt("teacher_idUser");
                int courseId = rs.getInt("course_idCourse");
                UserDAO udao = UserDAO.getInstance();
                User teacher = udao.getUserById(teacher_idUser);
                CourseDAO cdao = CourseDAO.getInstance();
                Course courses = cdao.getCourseById(courseId);
                group = new Group(name, teacher, courses);
                group.setIdGroup(id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return group;
    }

    public ArrayList<Group> studentInGroups(int studentId) {
        String sql = "SELECT * FROM quizmaster.studentsingroup WHERE student_idUser = ?;";
        ArrayList<Group> results = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                int groupId = rs.getInt("Group_idGroup");
                Group group = getGroupById(groupId);
                results.add(group);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }

    public void enrollStudentInGroup(Group group, int studentID) {
        String sql = "INSERT INTO `quizmaster`.`studentsingroup` (`group_idGroup`, `student_idUser`) VALUES (?, ?);";
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setInt(1, group.getIdGroup());
            ps.setInt(2, studentID);
            executeInsertPreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void withdrawStudentFromGroup(Group group, int studentID) {
        String sql = "DELETE FROM `quizmaster`.`studentsingroup` WHERE group_idGroup = ? AND student_idUser = ?";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, group.getIdGroup());
            ps.setInt(2, studentID);
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

    }
}
