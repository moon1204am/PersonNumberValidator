import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Acts as a placeholder for a real view that takes personal identity numbers as input from a text file with sample data.
 */
public class View {
    LogHandler logger = new LogHandler();
    File file = new File("sample-data.txt");
    Scanner sc = new Scanner(file);
    Validate validate = new Validate();

    public View() throws IOException {
    }

    public void runExecution() {
        while(sc.hasNext()) {
            String personNumber = sc.next();
            try {
                validate.validityCheck(personNumber);
            } catch (PersonNumberException prne) {
                handleException(prne.getMessage(), prne);
            }
        }
        sc.close();
    }

    private void handleException(String msg, Exception exc) {
        System.out.println(msg);
        logger.logException(exc);
    }
}
