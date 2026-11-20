package exception;

// Проверяемое исключение (наследуется от Exception)
public class DuplicateRentException extends Exception {
    public DuplicateRentException(String message) {
        super(message);
    }
}