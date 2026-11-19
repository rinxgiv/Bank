package notifiers;

public abstract class NotifierDecorator implements Notifiers {

    protected final Notifiers wrapped;

    public NotifierDecorator(Notifiers wrapped) {
        this.wrapped = wrapped;
    }

    public void notify(String message) {
        wrapped.notify(message);
    }
}
