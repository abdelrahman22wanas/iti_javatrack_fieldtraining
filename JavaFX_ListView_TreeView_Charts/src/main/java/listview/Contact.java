package listview;

/**
 * Simple model used to populate the customized ListView.
 */
public class Contact {

    private final String name;
    private final String email;
    private final String status; // "Online" / "Offline" / "Away"

    public Contact(String name, String email, String status) {
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}
