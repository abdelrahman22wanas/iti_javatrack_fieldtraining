package gui;

import dao.ContactDAO;
import model.ContactPerson;
import util.DataSourceUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Requirement 2 - GUI that retrieves all contacts (connection obtained from
 * a DataSource configured through db.properties) and lets the user browse
 * them one record at a time using First / Previous / Next / Last buttons.
 */
public class ContactViewerGUI extends JFrame {

    private final List<ContactPerson> contacts;
    private int currentIndex = 0;

    private final JTextField nameField = new JTextField(20);
    private final JTextField emailField = new JTextField(20);
    private final JTextField phoneField = new JTextField(20);

    private final JButton firstButton = new JButton("First");
    private final JButton prevButton = new JButton("Previous");
    private final JButton nextButton = new JButton("Next");
    private final JButton lastButton = new JButton("Last");

    public ContactViewerGUI(List<ContactPerson> contacts) {
        super("Contact Viewer");
        this.contacts = contacts;

        buildUI();
        showRecord(0);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 220);
        setLocationRelativeTo(null);
    }

    private void buildUI() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        nameField.setEditable(false);
        emailField.setEditable(false);
        phoneField.setEditable(false);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email ID:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("CellPhone:"));
        formPanel.add(phoneField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(firstButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(lastButton);

        firstButton.addActionListener(e -> showRecord(0));
        prevButton.addActionListener(e -> showRecord(currentIndex - 1));
        nextButton.addActionListener(e -> showRecord(currentIndex + 1));
        lastButton.addActionListener(e -> showRecord(contacts.size() - 1));

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showRecord(int index) {
        if (contacts.isEmpty()) {
            nameField.setText("");
            emailField.setText("");
            phoneField.setText("(no contacts found)");
            firstButton.setEnabled(false);
            prevButton.setEnabled(false);
            nextButton.setEnabled(false);
            lastButton.setEnabled(false);
            return;
        }

        currentIndex = Math.max(0, Math.min(index, contacts.size() - 1));
        ContactPerson c = contacts.get(currentIndex);

        nameField.setText(c.getName());
        emailField.setText(c.getEmail());
        phoneField.setText(c.getCellphone());

        boolean atFirst = currentIndex == 0;
        boolean atLast = currentIndex == contacts.size() - 1;

        firstButton.setEnabled(!atFirst);
        prevButton.setEnabled(!atFirst);
        nextButton.setEnabled(!atLast);
        lastButton.setEnabled(!atLast);
    }

    public static void main(String[] args) {
        // Connection is obtained from a DataSource built from db.properties
        // (see util.DataSourceUtil) — nothing is hard-coded here.
        ContactDAO dao = new ContactDAO(DataSourceUtil.getDataSource());
        List<ContactPerson> contacts = dao.getAllContacts();

        SwingUtilities.invokeLater(() -> new ContactViewerGUI(contacts).setVisible(true));
    }
}
