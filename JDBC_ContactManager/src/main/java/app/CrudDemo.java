package app;

import dao.ContactDAO;
import model.ContactPerson;

import java.util.List;

/**
 * Requirement 1 - Exercises all five ContactDAO operations from a main method.
 * Run sql/schema.sql against your MySQL server first, and update the
 * DRIVER/URL/USER/PASSWORD constants in ContactDAO to match your setup.
 */
public class CrudDemo {

    public static void main(String[] args) {
        ContactDAO dao = new ContactDAO();

        System.out.println("--- Retrieve all contacts ---");
        for (ContactPerson c : dao.getAllContacts()) {
            System.out.println(c);
        }

        System.out.println("\n--- Retrieve contact by id=1 ---");
        ContactPerson one = dao.getContactById(1);
        System.out.println(one != null ? one : "Not found");

        System.out.println("\n--- Insert a new contact ---");
        int newId = dao.insertContact(new ContactPerson("Sara", "sara@mail.com", "01234567890"));
        System.out.println(newId > 0 ? "1 row inserted. New contact id = " + newId : "Insert failed.");

        System.out.println("\n--- Edit a contact (id=1) ---");
        boolean updated = dao.editContact(new ContactPerson(1, "ITI", "info@iti.eg", "01005628775"));
        System.out.println(updated ? "1 row updated. Contact 1 email is now: info@iti.eg" : "Update failed.");

        System.out.println("\n--- Delete a contact (id=" + newId + ") ---");
        boolean deleted = dao.deleteContact(newId);
        System.out.println(deleted ? "1 row deleted. Contact " + newId + " removed." : "Delete failed.");

        System.out.println("\n--- Final state ---");
        for (ContactPerson c : dao.getAllContacts()) {
            System.out.println(c);
        }
    }
}
