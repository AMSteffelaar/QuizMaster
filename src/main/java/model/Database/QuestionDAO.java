package model.Database;


import model.entity.Course;
import model.entity.Question;
import model.entity.Quiz;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDAO extends AbstractDAO {

    private static QuestionDAO questdao;

    public QuestionDAO(DBaccess db) {
        super(db);
    }

    public static QuestionDAO getInstance() {
        if (questdao == null) {
            questdao = new QuestionDAO(db.getInstance());
            return questdao;
        } else {
            return questdao;
        }
    }

    public ArrayList<Question> getQuestionsByQuiz(int idQuiz) {
        String sql = "SELECT * from question where Quiz_idQuiz = ?";
        ArrayList<Question> results = null;
        Question result;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, idQuiz);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                int quizQuiz = rs.getInt("Quiz_idQuiz");
                String question = rs.getString("question");
                String answerA = rs.getString("answerA");
                String answerB = rs.getString("answerB");
                String answerC = rs.getString("answerC");
                String answerD = rs.getString("answerD");
                QuizDAO qdao = QuizDAO.getInstance();
                Quiz quiz = qdao.getQuizById(quizQuiz);
                result = new Question(quiz, question, answerA, answerB, answerC, answerD);
                result.setIdQA(quizQuiz);
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }

    public Question getQuestionById(int id) {
        String sql = "Select * from quiz where idQuiz = ?";
        Question question = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = executeSelectPreparedStatement(ps);
            while (rs.next()) {
                int quizQuiz = rs.getInt("Quiz_idQuiz");
                String question1 = rs.getString("question");
                String answerA = rs.getString("answerA");
                String answerB = rs.getString("answerB");
                String answerC = rs.getString("answerC");
                String answerD = rs.getString("answerD");
                QuizDAO qdao = QuizDAO.getInstance();
                Quiz quiz = qdao.getQuizById(quizQuiz);
                question = new Question(quiz, question1, answerA, answerB, answerC, answerD);
                question.setIdQA(id);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return question;
    }

    public void storeQuestion(int quizId, int questionid, String naam) {
        String sql = "INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `idQuestion`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setInt(1, quizId);
            ps.setInt(2, questionid);
            ps.setString(3, naam);
            executeInsertPreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}


