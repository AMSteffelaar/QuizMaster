package model.Database;

import model.entity.User.Administrator;
import model.entity.User.Teacher;
import model.entity.User.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private UserDAO udao;

    @BeforeEach
    void setUp() {
        udao = UserDAO.getInstance();
    }

    @Test
    void createUser() {
        //Arrange:
        String name = "Jaap";
        String password = "jaap";
        String role = "Administrator";

        //Act:
        User user = udao.createUser(name, password,role);

        //Assert:
        assertEquals("Jaap",user.getName());
        assertEquals("jaap",user.getPassword());
        assertEquals("Administrator",user.getRole());
        assertTrue(user instanceof Administrator);
    }

    //Edgecase: user zonder naam maken.
    @Test
    void createUserWithEmptyName() {
        User user = udao.createUser("","bla","Teacher");
        assertEquals("",user.getName());
        assertEquals("bla",user.getPassword());
        assertEquals("Teacher",user.getRole());
        assertTrue(user instanceof Teacher);
    }

    //Edgecase: typefout in rolnaam.
    @Test
    void createUserWithTypoInRole() {
        User user = udao.createUser("Jaap", "jaap","Administrater");
        assertEquals(null, user);
        //Zelfde als: assertNull(user);
    }

    //Clean up:
    @AfterEach
    void tearDown() {
        udao = null;
    }
}