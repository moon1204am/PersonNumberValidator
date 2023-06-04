/**
 * Thrown when a personal identity number is not valid, or it's control digit is incorrect, explaining the reason it went wrong.
 */
public class PersonNumberException extends Exception {
    public PersonNumberException(String reason) {
        super(reason);
    }
}
