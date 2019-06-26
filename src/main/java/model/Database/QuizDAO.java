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
            qdao = new QuizDAO(db.getInstance());
            return qdao;
        } else {
            return qdao;
        }
    }

    public ArrayList<Quiz> getQuizByCourse(int idCourse) {
        String sql = "SELECT * from quiz where course_idCourse = ?";
        ArrayList<Quiz> results = null;
        Quiz result;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, idCourse);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                int id_Quiz = rs.getInt("idQuiz");
                int courseCourse = rs.getInt("course_idCourse");
                String quizName = rs.getString("quizname");
                int numberOfQuestions = rs.getInt("numberOfQuestions");
                int treshold = rs.getInt("treshold");
                CourseDAO cdao = CourseDAO.getInstance();
                Course course = cdao.getCourseById(courseCourse);
                result = new Quiz(course, quizName,numberOfQuestions,treshold);
                result.setIdQuiz(id_Quiz);
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
                int course_idCourse = rs.getInt("course_idCourse");
                String name = rs.getString("quizname");
                int numberOfQuestions = rs.getInt("numberOfQuestions");
                int treshold = rs.getInt("treshold");
                CourseDAO cdao = CourseDAO.getInstance();
                Course course = cdao.getCourseById(course_idCourse);
                quiz = new Quiz(course, name,numberOfQuestions,treshold);
                quiz.setIdQuiz(id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return quiz;
    }


    public void storeQuiz (Quiz quiz){
    String sql = "INSERT INTO `quizmaster`.`quiz` (course_idCourse, quizName, numberOfQuestions, treshold) VALUES (?, ?, ?, ?);";
        try {
        PreparedStatement ps = getStatementWithKey(sql);
        ps.setInt(1, quiz.getCourse().getIdCourse());
        ps.setString(2, quiz.getName());
        ps.setInt(3, quiz.getNumberOfQuestions());
        ps.setInt(4, quiz.getTreshold());
        int id = executeInsertPreparedStatement(ps);
        quiz.setIdQuiz(id);
    } catch (SQLException e) {
        System.out.println("SQL error: " + e.getMessage());
    }}
    public void updateNumberOfQuestions (Quiz quiz){
        String sql = "UPDATE `quizmaster`.`quiz` SET `numberOfQuestions` = '?' WHERE (`idQuiz` = '?');";
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setInt(1, quiz.getNumberOfQuestions());
            ps.setInt(2, quiz.getIdQuiz());
            executeInsertPreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }}
}


