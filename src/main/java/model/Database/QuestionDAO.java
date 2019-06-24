package model.Database;

import model.entity.Question;
import view.Main;

public class QuestionDAO extends AbstractDAO {

    public static QuestionDAO getInstance(){
        if(quesdao==null){
            quesdao = new QuestionDAO(Main.getInstance());
            return quesdao;
        }
        else {
            return quesdao;
        }
    }




}
