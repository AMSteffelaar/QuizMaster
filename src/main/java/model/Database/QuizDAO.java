package model.Database;

import model.entity.Course;
import model.entity.Quiz;
import model.entity.User.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizDAO extends AbstractDAO {

    private static QuizDAO qdao;

    public QuizDAO(DBaccess db) {
        super(db);
    }

    public static QuizDAO getInstance() {
        if (qdao == null) {
            qdao = new QuizDAO(Main.getInstance());
            return qdao;
        } else {
            return qdao;
        }
    }

    public ArrayList<Quiz> getQuizByCourse(int idCourse) {
        String sql = "Select * from quiz where course_idCourse = ?";
        ArrayList<Quiz> results = null;
        Quiz result;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, idCourse);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                int courseCourse = rs.getInt("course_idCourse");
                String quizName = rs.getString("quizname");
                CourseDAO cdao = CourseDAO.getInstance();
                Course course = cdao.getCourseById(courseCourse);
                result = new Quiz(course, quizName);
                result.setIdQuiz(courseCourse);
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }

    public Quiz getQuizById(int id) {
        String sql = "Select * from quiz where idQuiz = ?";
        Quiz quiz = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                String name = rs.getString("name");
                int course_idCourse = rs.getInt("course_idCourse");
                CourseDAO cdao = CourseDAO.getInstance();
                Course course = cdao.getCourseById(course_idCourse);
                quiz = new Quiz(course, name);
                quiz.setIdQuiz(id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return quiz;
    }
}
