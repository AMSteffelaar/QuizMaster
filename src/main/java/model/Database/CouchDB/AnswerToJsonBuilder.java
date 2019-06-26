package model.Database.CouchDB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.entity.Answer;
import model.entity.Quiz;

public class AnswerToJsonBuilder {
    private CouchDBaccess cdb;

    public AnswerToJsonBuilder(CouchDBaccess cdb) {
        super();
        this.cdb = cdb;
    }

    public void saveAnswer(Answer answer) {
        Gson gson = new Gson();
        String jsonstring = gson.toJson(answer);
        System.out.println(jsonstring);
        JsonParser parser = new JsonParser();
        JsonObject jsonobject = parser.parse(jsonstring).getAsJsonObject();
        cdb.saveDocument(jsonobject);
    }
}

