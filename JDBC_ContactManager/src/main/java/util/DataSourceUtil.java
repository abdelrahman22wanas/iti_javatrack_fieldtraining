package util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Requirement 2 - Builds a javax.sql.DataSource whose settings (driver,
 * url, user, password) come from an external properties file
 * (src/main/resources/db.properties), instead of being hard-coded.
 */
public class DataSourceUtil {

    private static final String PROPERTIES_FILE = "/db.properties";

    public static DataSource getDataSource() {
        Properties props = loadProperties();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(props.getProperty("db.url"));
        dataSource.setUser(props.getProperty("db.user"));
        dataSource.setPassword(props.getProperty("db.password"));
        return dataSource;
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = DataSourceUtil.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("Cannot find " + PROPERTIES_FILE + " on the classpath.");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + PROPERTIES_FILE, e);
        }
        return props;
    }
}
