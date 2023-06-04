import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for handling the exception logging when a person number,
 * coordination number or organisation number is invalid.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "person-number-log.txt";
    private PrintWriter log;

    public LogHandler() throws IOException {
        log = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
    }

    /**
     * Logs to the specified file describing the exception.
     * @param exception The exception that was thrown
     */
    public void logException(Exception exception) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(createTime());
        logMessage.append(", Exception was thrown: ");
        logMessage.append(exception.getMessage());
        log.println(logMessage);
        System.out.println(logMessage);
        exception.printStackTrace(log);
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}