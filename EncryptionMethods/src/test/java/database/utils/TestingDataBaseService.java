package database.utils;

import database.service.DataBaseService;
import org.hibernate.cfg.Configuration;

/**
 * Created by Алексей on 27.12.2016.
 */
public class TestingDataBaseService extends DataBaseService {
    private static String CONNECTION_TESTING_URL = "jdbc:sqlite:../database/test.db";
    private TestingDataBaseService() {
        super();
    }

    public static synchronized DataBaseService instanceDataBaseService() {
        if (service == null) {
            service = new TestingDataBaseService();
        }
        return service;
    }

    @Override
    public Configuration getSqliteConfiguration() {
        Configuration configuration = super.getSqliteConfiguration();
        configuration.setProperty("hibernate.connection.url", CONNECTION_TESTING_URL);
        return configuration;
    }

}
