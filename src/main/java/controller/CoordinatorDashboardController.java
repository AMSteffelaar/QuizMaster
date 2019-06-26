package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Database.CourseDAO;
import model.Database.QuestionDAO;
import model.Database.QuizDAO;
import model.entity.*;
import view.SceneManager;

import java.util.ArrayList;


public class CoordinatorDashboardController {
    SceneManager manager = SceneManager.getSceneManager();
    CourseDAO cdao = CourseDAO.getInstance();
    QuizDAO qdao = QuizDAO.getInstance();
    QuestionDAO qudao = QuestionDAO.getInstance();

    @FXML
    private Button menuButton;

    @FXML
    private Button newCourseButton;

    @FXML
    private Button editCourseButton;

    @FXML
    private Button newQuizButton;

    @FXML
    private Button editQuizButton;

    @FXML
    private Button newQuestionButton;

    @FXML
    private Button editQuestionButton;

    @FXML
    private ListView<Course> courseList;

    @FXML
    private ListView<Quiz> quizList;

    @FXML
    private ListView<Question> questionList;

    public void setup() {
        populateCourses();
        courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course s, Course t1) {
                System.out.println("Selected item in courseList: " + observableValue + ", " + s + ", " + t1);
                populateQuiz(t1);
            }
        });
        courseList.setCellFactory(param -> new ListCell<Course>() {
            @Override
            protected void updateItem(Course cou, boolean empty) {
                super.updateItem(cou, empty);
                if (empty || cou == null || cou.getName() == null) {
                    setText(" ");
                } else {
                    setText(cou.getName());
                    courseList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Course> observable, Course oldValue, Course newValue) -> {
                        if (courseList.isFocused()) ;
                    });
                }
            }
        });
        quizList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Quiz>() {
            @Override
            public void changed(ObservableValue<? extends Quiz> observableValue, Quiz a, Quiz t2) {
                System.out.println("Selected item in quizList: " + observableValue + ", " + a + ", " + t2);
                populateQuestion(t2);
            }
        });

        quizList.setCellFactory(param -> new ListCell<Quiz>() {
            @Override
            protected void updateItem(Quiz qui, boolean empty) {
                super.updateItem(qui, empty);
                if (empty || qui == null || qui.getName() == null) {
                    setText(" ");
                } else {
                    setText(qui.getName());
                    quizList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Quiz> observable, Quiz oldValue, Quiz newValue) -> {
                        if (quizList.isFocused()) ;
                    });
                }
            }
        });
        questionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Question>() {
            @Override
            public void changed(ObservableValue<? extends Question> observableValue, Question s, Question t3) {
                System.out.println("Selected item in questionList: " + observableValue + ", " + s + ", " + t3);
            }
        });

        questionList.setCellFactory(param -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question quis, boolean empty) {
                super.updateItem(quis, empty);
                if (empty || quis == null || quis.getQuestion() == null) {
                    setText(" ");
                } else {
                    setText(quis.getQuestion());
                    questionList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Question> observable, Question oldValue, Question newValue) -> {
                        if (courseList.isFocused()) ;
                    });
                }
            }
        });
    }


    public void doMenu() {
        manager.showWelcomeScene();
    }

    public void doNewCourse() {
        manager.showNewCourseScene();
    }

    public void doEditCourse() {
        /*manager.showChangeCourseScene(CourseDAO.getInstance().getCourseByName(courseList.getSelectionModel().getSelectedItem()));*/
    }

    public void doNewQuiz() {
    }

    public void doEditQuiz() {
    }

    public void doNewQuestion(ActionEvent event) {
        Question question = questionList.getSelectionModel().getSelectedItem();
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        manager.showNewQuestion(quiz, question);
    }


    public void doEditQuestion() {
    }


    private void populateCourses() {
        ObservableList<Course> cursus = FXCollections.observableArrayList();
        ArrayList<Course> courses = cdao.getCoursesByCoordinator(Session.getInstance().getCurrentUser().getId());
        cursus.setAll(courses);
        courseList.setItems(cursus);
    }


    private void populateQuiz(Course c) {
        int idcourse = c.getIdCourse();
        ObservableList<Quiz> quizeses = FXCollections.observableArrayList();
        ArrayList<Quiz> quizes = qdao.getQuizByCourse(idcourse);
        quizeses.setAll(quizes);
        quizList.setItems(quizeses);
    }


    private void populateQuestion(Quiz q) {
        q.getIdQuiz();
        ObservableList<Question> questionses = FXCollections.observableArrayList();
        ArrayList<Question> questions = qudao.getQuestionsByQuiz(q.getIdQuiz());
        questionses.setAll(questions);
        questionList.setItems(questionses);
    }
}
