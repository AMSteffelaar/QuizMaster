module QuizMaster {
  requires javafx.fxml;
  requires javafx.controls;
  requires javafx.graphics;
  requires java.sql;
<<<<<<< HEAD
=======

>>>>>>> CoordinatorDashboard
  opens view to javafx.graphics, javafx.fxml;
  opens controller to javafx.fxml;
}