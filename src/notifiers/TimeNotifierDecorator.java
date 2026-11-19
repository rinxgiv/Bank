package notifiers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeNotifierDecorator extends NotifierDecorator {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    public TimeNotifierDecorator(Notifiers wrapped) {
        super(wrapped);
    }

    public void notify(String message) {
        super.notify(message + " " + LocalTime.now().format(TIME_FORMAT));
    }
}
