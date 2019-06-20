package model.Database;

import model.entity.Course;
import model.entity.Group;
import model.entity.User.Coordinator;
import model.entity.User.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDAO extends AbstractDAO{
    private static GroupDAO gdao;

    public GroupDAO(DBaccess db) {
        super(db);
    }

    /*public int getUserIdByNamePassword(String name, String password) {
        String sql = "Select * from user where name = ? and password = ?";
        int id = 0;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()) {
                id = rs.getInt("idUser");

            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return id;
    }*/



    //deze methode levert alle bestaande groups
    //deze methode levert: een arrayList van de bestaande groups
    public ArrayList<Group> getGroups() {
        String sql = "Select * from group";
        ArrayList<Group> results = null;
        Course result;
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            results = new ArrayList<>();
            while (rs.next()) {
                int groupId = rs.getInt("idGroup");
                int course_idCourse = rs.getInt("course_idCourse");
                int teacher_idUser = rs.getInt("teacher_idUser");
                String courseName = rs.getString("name");
                UserDAO udao = UserDAO.getInstance();
                String udao_name = udao.getUserNameById(teacher_idUser);
                String udao_password = udao.getUserPasswordById(teacher_idUser);
                //maak een user die een coordinator is
                User user = new Coordinator(udao_name,udao_password);
                //maak een course

                result = new Group(courseName, user, );
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return results;
    }


    public static GroupDAO getInstance(){
        if(gdao==null){
            gdao = new GroupDAO(Main.getInstance());
            return gdao;
        }
        else {
            return gdao;
        }
    }
}
