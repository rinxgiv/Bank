package notifiers;

public class ConsoleNotifier implements Notifiers {
    public void notify(String message) {
        System.out.println(" Console Notification: " + message);
    }
}
