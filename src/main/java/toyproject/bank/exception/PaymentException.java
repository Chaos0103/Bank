package toyproject.bank.exception;

public class PaymentException extends IllegalStateException {
    public PaymentException() {
        super();
    }

    public PaymentException(String s) {
        super(s);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentException(Throwable cause) {
        super(cause);
    }
}
