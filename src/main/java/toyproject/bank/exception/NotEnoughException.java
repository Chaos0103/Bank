package toyproject.bank.exception;

public class NotEnoughException extends IllegalStateException {
    public NotEnoughException() {
        super();
    }

    public NotEnoughException(String s) {
        super(s);
    }

    public NotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughException(Throwable cause) {
        super(cause);
    }
}
