package jab.aoc;

public class AOCException extends RuntimeException {

    public AOCException(String message) {
        super(message);
    }

    public AOCException(String message, Throwable cause) {
        super(message, cause);
    }
}
