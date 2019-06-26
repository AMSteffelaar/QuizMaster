module QuizMaster {
  requires javafx.fxml;
  requires javafx.controls;
  requires javafx.graphics;
  requires java.sql;
  /*requires org.junit.jupiter.api;*/
  requires gson;
  requires lightcouch;
  opens view to javafx.graphics, javafx.fxml;
  opens controller to javafx.fxml;
}