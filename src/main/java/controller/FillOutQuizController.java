package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Database.CouchDB.CouchDBaccess;
import model.Database.CouchDB.AnswerToJsonBuilder;
import model.Database.QuestionDAO;
import model.entity.Answer;
import model.entity.Question;
import model.entity.Quiz;
import model.entity.Session;
import model.entity.User.Student;
import view.SceneManager;

import java.util.ArrayList;

public class FillOutQuizController {

    private int idQuiz;

    private int currentQuestion = 0;

    private ArrayList<Question> questions = new ArrayList<>();

    SceneManager manager = SceneManager.getSceneManager();
    AnswerToJsonBuilder quizCouch = new AnswerToJsonBuilder(CouchDBaccess.getInstance());
    Answer answers;

    @FXML
    private Label title;
    @FXML
    private Label questionNumber;
    @FXML
    private TextArea questionArea;
    @FXML
    private Button answerAButton;
    @FXML
    private Button answerBButton;
    @FXML
    private Button answerCButton;
    @FXML
    private Button answerDButton;
    @FXML
    private Button nextQuestionButton;
    @FXML
    private Button previousQuestionButton;
    @FXML
    private Button finishQuizButton;
    @FXML
    private Button menuButton;

    public void setup(Quiz quiz) {
        answers = new Answer(quiz, (Student)Session.getInstance().getCurrentUser());
        QuestionDAO qdao = QuestionDAO.getInstance();
        questions = qdao.getQuestionsByQuiz(quiz.getIdQuiz());
        showQuestion(questions.get(currentQuestion));
    }

    public void showQuestion(Question question) {
        resetButtonColours();
        questionNumber.setText(String.format("%d",(currentQuestion+1)));
        if (currentQuestion == 0) {
            previousQuestionButton.setDisable(true);
        } else {
            previousQuestionButton.setDisable(false);
        }
        if(currentQuestion == questions.size() - 1) {
            nextQuestionButton.setVisible(false);
            finishQuizButton.setVisible(true);

        } else {
            nextQuestionButton.setVisible(true);
            finishQuizButton.setVisible(false);

        }
        question.shuffleAnswers();
        String questionText = String.format("%s\n\nA) %s\nB) %s\nC) %s\nD) %s",question.getQuestion(),
                question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());
        questionArea.setText(questionText);
    }


    private void registerAnswer(char answer) {
        Question question = questions.get(currentQuestion);
        answers.setVragen(question, currentQuestion);
        String a = Character.toString(answer);
        if (answer == question.getCorrectAnswer()) {
            answers.setAntwoord(a, currentQuestion);
        } else {
            answers.setAntwoord(a, currentQuestion);
            //Update couchDB met fout antwoord.
        }
    }


    public void registerA() {
        resetButtonColours();
        answerAButton.setStyle("-fx-background-color: slategray;-fx-text-fill: white");
        registerAnswer('A');
    }

    public void registerB() {
        resetButtonColours();
        answerBButton.setStyle("-fx-background-color: slategray;-fx-text-fill: white");
        registerAnswer('B');
    }

    public void registerC() {
        resetButtonColours();
        answerCButton.setStyle("-fx-background-color: slategray;-fx-text-fill: white");
        registerAnswer('C');
    }

    public void registerD() {
        resetButtonColours();
        answerDButton.setStyle("-fx-background-color: slategray;-fx-text-fill: white");
        registerAnswer('D');
    }

    private void resetButtonColours(){
        answerAButton.setStyle("");
        answerBButton.setStyle("");
        answerCButton.setStyle("");
        answerDButton.setStyle("");
    }

    public void doNextQuestion() {
        currentQuestion++;
        showQuestion(questions.get(currentQuestion));

    }

    public void doPreviousQuestion() {
        currentQuestion--;
        showQuestion(questions.get(currentQuestion));
    }

    public void doFinishQuiz () {
        Quiz quiz = questions.get(0).getQuiz();
        System.out.println(answers);
        quizCouch.saveAnswer(answers);
        manager.showStudentFeedback(quiz);
    }

    public void doMenu() {
        manager.showWelcomeScene();
    }
}
