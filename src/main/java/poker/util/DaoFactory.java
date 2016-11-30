package poker.util;


import poker.persistence.AbstractDao;
/**
 * Created by student on 11/29/16.
 */
public class DaoFactory {

    // Empty constructor is private - static class
    private DaoFactory() {

    }

    public static AbstractDao createDao(Class type) {
        return new AbstractDao(type);

    }
}
