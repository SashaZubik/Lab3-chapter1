package exception;

// Непроверяемое исключение (RuntimeException)
public class InvalidRentException extends RuntimeException {
    public InvalidRentException(String message) {
        super(message);
    }
}
