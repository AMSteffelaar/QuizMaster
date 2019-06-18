package model.entity;

import model.entity.User.User;

/**
 * Deze class zorgt ervoor dat de currentUser 'onthouden' kan worden als iemand inlogt,
 * want anders vergeet java wie er heeft ingelogt als je van het ene naar het andere scherm springt.
 */
public class Session {

    private User currentUser;

    private static Session instance =  null;

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
