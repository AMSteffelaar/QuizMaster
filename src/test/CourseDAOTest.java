import model.Database.CourseDAO;
import model.Database.DBaccess;
import model.entity.Course;
import model.entity.User.Coordinator;
import model.entity.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
class CourseDAOTest {

    private DBaccess db = new DBaccess();


    @Test
    void getCourseByName() {
        db.openConnection();
        //actual
        User coordinator = new Coordinator("Mark", "mark");
        Course course = new Course("Bouldercursus", coordinator);
        CourseDAO cdao = CourseDAO.getInstance();
        Course course_actual = cdao.getInstance().getCourseByName(course.getName());
        String naam_actual = course_actual.getName();
        int id_actual = course_actual.getIdCourse();

        //expected
        Course course1 = new Course("Bouldercursus",coordinator);
        String naam = course1.getName();
        int id = course1.getIdCourse();

        //controle dmv assert
        assertEquals(naam_actual,naam);
        assertEquals(id_actual,id);
        assertTrue(course_actual instanceof Course);

        db.closeConnection();
    }
}*/
