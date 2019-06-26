package model.Database.CouchDB;

import com.google.gson.JsonObject;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.Response;

public class CouchDBaccess {
    private static CouchDBaccess cdb;
    private CouchDbClient client;

    public static CouchDBaccess getInstance() {
        if (cdb == null) {
            cdb = new CouchDBaccess();
            return cdb;
        } else {
            return cdb;
        }
    }

    public void setupConnection() {
        CouchDbProperties properties = new CouchDbProperties();

        //Set the database name
        properties.setDbName("quizmaster");

        //Create the database if it didn't already exist
        properties.setCreateDbIfNotExist(true);

        //Server is running on localhost
        properties.setHost("localhost");

        //Set the port CouchDB is running on (5984)
        properties.setPort(5984);

        properties.setProtocol("http");

        //uncomment to set the username
        // properties.setUsername("username");
        //uncomment to set the password
        // properties.setPassword("password");
        //Create the database client and setup the connection with given
        //properties
        client = new CouchDbClient(properties);
    }

    public String saveDocument(JsonObject document) {
        Response response = client.save(document);
        return response.getId();
    }

    public void close() {
        try {
            client.shutdown();
            System.out.println("Connectie CouchDB afgesloten");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void run() {
        try {
            cdb.setupConnection();
            System.out.println("Connection open");
        } catch (Exception e) {
            System.out.println("\nEr is iets fout gegaan\n");
            e.printStackTrace();
        }
    }
}
