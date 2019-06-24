package model.Database;

import model.entity.Course;
import model.entity.Group;
import model.entity.Question;
import model.entity.Quiz;
import model.entity.User.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDAO extends AbstractDAO {

    private static QuestionDAO questdao;

    public QuestionDAO(DBaccess db) {super(db);}

        public static QuestionDAO getInstance(){
        if(questdao==null){
            questdao = new QuestionDAO(Main.getInstance());
            return questdao;
        }
        else {
            return questdao;
        }
    }

   /* public ArrayList<Question> getQuestionbyQuiz(int idQuiz) {
        String sql = "SELECT * FROM q&a where Quiz_idQuiz = ?";
        ArrayList<Question> results = null;
        Question result;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, idQuiz);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                int quiz_idQuiz = rs.getInt("Quiz_idQuiz");
                String question = rs.getString("question");
                QuizDAO qdao = QuizDAO.getInstance();
                Quiz quiz = qdao.getQuizById(quiz_idQuiz);
                result = new Question(question, quiz);
                result.setQuestion(questionId);
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;*/
    }


