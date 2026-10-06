package dao;

import model.ContactPerson;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the `contact` table.
 * <p>
 * Requirement 1: holds the connection string / driver / username / password
 * and exposes the five basic CRUD operations. Nothing outside this class
 * talks to the database directly.
 * <p>
 * Requirement 2: {@link #ContactDAO(DataSource)} lets the same class obtain
 * its connections from an externally-configured DataSource instead.
 * <p>
 * Requirement 3: adds a batch update and two RowSet-based retrieval methods.
 */
public class ContactDAO {

    // Requirement 1 - hard-coded vendor-specific JDBC driver/connection info.
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/contactdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "MaNaWaNa2006$$$";

    // Requirement 2 - optional externally-configured DataSource.
    private final DataSource dataSource;

    /** Requirement 1 constructor: uses the hard-coded driver connection. */
    public ContactDAO() {
        this.dataSource = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not found on classpath.", e);
        }
    }

    /** Requirement 2 constructor: uses a DataSource built from an external properties file. */
    public ContactDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        if (dataSource != null) {
            return dataSource.getConnection();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ---------------------------------------------------------------
    // Requirement 1: CRUD operations
    // ---------------------------------------------------------------

    public List<ContactPerson> getAllContacts() {
        List<ContactPerson> contacts = new ArrayList<>();
        String sql = "SELECT id, name, email, cellphone FROM contact ORDER BY id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contacts.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all contacts", e);
        }
        return contacts;
    }

    public ContactPerson getContactById(int id) {
        String sql = "SELECT id, name, email, cellphone FROM contact WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving contact " + id, e);
        }
        return null;
    }

    /** Inserts a new contact and returns the auto-generated id (or -1 on failure). */
    public int insertContact(ContactPerson contact) {
        String sql = "INSERT INTO contact (name, email, cellphone) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getCellphone());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting contact", e);
        }
        return -1;
    }

    public boolean editContact(ContactPerson contact) {
        String sql = "UPDATE contact SET name = ?, email = ?, cellphone = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getCellphone());
            ps.setInt(4, contact.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating contact " + contact.getId(), e);
        }
    }

    public boolean deleteContact(int id) {
        String sql = "DELETE FROM contact WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting contact " + id, e);
        }
    }

    private ContactPerson mapRow(ResultSet rs) throws SQLException {
        return new ContactPerson(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("cellphone"));
    }

    // ---------------------------------------------------------------
    // Requirement 3a: Batch update - change the email field for two
    // different names ("ITI" and "MCIT"), sent together as one batch.
    // ---------------------------------------------------------------

    public int[] batchUpdateEmails(String itiNewEmail, String mcitNewEmail) {
        String sql = "UPDATE contact SET email = ? WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            ps.setString(1, itiNewEmail);
            ps.setString(2, "ITI");
            ps.addBatch();

            ps.setString(1, mcitNewEmail);
            ps.setString(2, "MCIT");
            ps.addBatch();

            int[] rowsAffected = ps.executeBatch();
            conn.commit();
            return rowsAffected;

        } catch (SQLException e) {
            throw new RuntimeException("Error executing batch update", e);
        }
    }

    // ---------------------------------------------------------------
    // Requirement 3b: JdbcRowSet - connected, scrollable/updatable
    // in-memory RowSet.
    // ---------------------------------------------------------------

    public List<ContactPerson> getAllContactsViaJdbcRowSet() {
        List<ContactPerson> contacts = new ArrayList<>();
        try {
            JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
            rowSet.setUrl(URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASSWORD);
            rowSet.setCommand("SELECT id, name, email, cellphone FROM contact ORDER BY id");
            rowSet.execute();

            while (rowSet.next()) {
                contacts.add(new ContactPerson(
                        rowSet.getInt("id"),
                        rowSet.getString("name"),
                        rowSet.getString("email"),
                        rowSet.getString("cellphone")));
            }
            rowSet.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error using JdbcRowSet", e);
        }
        return contacts;
    }

    // ---------------------------------------------------------------
    // Requirement 3c: WebRowSet - disconnected RowSet, saved to XML.
    // ---------------------------------------------------------------

    public void exportContactsToXml(String xmlFilePath) {
        try {
            WebRowSet webRowSet = RowSetProvider.newFactory().createWebRowSet();
            webRowSet.setUrl(URL);
            webRowSet.setUsername(USER);
            webRowSet.setPassword(PASSWORD);
            webRowSet.setCommand("SELECT id, name, email, cellphone FROM contact ORDER BY id");
            webRowSet.execute();

            try (FileOutputStream fos = new FileOutputStream(xmlFilePath)) {
                webRowSet.writeXml(fos);
            }
            webRowSet.close();
            System.out.println("Contacts exported to " + xmlFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Error exporting contacts to XML", e);
        }
    }
}
