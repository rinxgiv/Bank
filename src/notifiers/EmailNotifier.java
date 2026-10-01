package notifiers;

public class EmailNotifier implements Notifiers {
    public void notify(String message) {
        System.out.println(" Email Notification: " + message);
    }
}
