package exception;

public class NullValueException extends RuntimeException {

    public NullValueException() {
        super();
    }

    public NullValueException(String message) {
        super(message);
    }
}