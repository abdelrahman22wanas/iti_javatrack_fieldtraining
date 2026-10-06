package model;

/**
 * Requirement 1 - Domain object (POJO) mapping the `contact` table.
 */
public class ContactPerson {

    private int id;
    private String name;
    private String email;
    private String cellphone;

    public ContactPerson() {
    }

    public ContactPerson(int id, String name, String email, String cellphone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
    }

    /** Convenience constructor for inserts, where the id is auto-generated. */
    public ContactPerson(String name, String email, String cellphone) {
        this(0, name, email, cellphone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + email + " | " + cellphone;
    }
}
