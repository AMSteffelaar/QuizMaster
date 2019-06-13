package model.Database;

public class DbLauncher {
    private DBaccess db;
    private UserDAO udao;

    public DbLauncher() {
        super();
        db = new DBaccess();
        udao = new UserDAO(db);
    }

    public DBaccess getDb() {
        return db;
    }

    public UserDAO getUdao() {
        return udao;
    }
}
