package controller;

import javafx.event.ActionEvent;
import model.entity.Course;

public class ChangeCourseController {

  public void setup(Course course) {}

    public void doMenu(ActionEvent event) {
        System.out.println(event);
        System.out.println("wat een toestand");
    }

    public void doChangeCourse(ActionEvent event) {
        System.out.println("verandering aangebracht");
    }
}
