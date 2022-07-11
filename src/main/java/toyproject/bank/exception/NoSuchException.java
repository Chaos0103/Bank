package toyproject.bank.exception;

public class NoSuchException extends IllegalStateException {

    public NoSuchException() {
        super();
    }

    public NoSuchException(String s) {
        super(s);
    }

    public NoSuchException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchException(Throwable cause) {
        super(cause);
    }
}
