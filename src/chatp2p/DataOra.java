package chatp2p;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pacie
 */
public class DataOra {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm-ss");

    public static String getDate() {
        return LocalDate.now().format(dateFormat);
    }

    public static String getTime() {
        return LocalTime.now().format(timeFormat);
    }

    public static String getDateAndTime() {
        return LocalDateTime.now().format(dateTimeFormat);
    }
}
