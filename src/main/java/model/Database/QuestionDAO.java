package model.Database;


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
            questdao = new QuestionDAO(Main.getInstance());
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
}


