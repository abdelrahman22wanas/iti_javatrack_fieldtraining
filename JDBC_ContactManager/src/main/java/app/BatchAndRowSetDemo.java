package app;

import dao.ContactDAO;
import model.ContactPerson;

import java.util.Arrays;
import java.util.List;

/**
 * Requirement 3 - Exercises the batch update and RowSet-based operations.
 */
public class BatchAndRowSetDemo {

    public static void main(String[] args) {
        ContactDAO dao = new ContactDAO();

        System.out.println("--- Batch update emails for ITI and MCIT ---");
        int[] rowsAffected = dao.batchUpdateEmails("iti@iti.gov.eg", "info@mcit.gov.eg");
        System.out.println("Rows affected: " + Arrays.toString(rowsAffected));

        System.out.println("\n--- JDBCRowSet: view all contacts ---");
        List<ContactPerson> viaRowSet = dao.getAllContactsViaJdbcRowSet();
        for (ContactPerson c : viaRowSet) {
            System.out.println(c);
        }

        System.out.println("\n--- WebRowSet: save result to XML ---");
        dao.exportContactsToXml("contacts.xml");
    }
}
