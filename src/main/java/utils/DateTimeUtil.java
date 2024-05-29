package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for handling date and time operations.
 */
public class DateTimeUtil {
    /**
     * Combines a date with specified hour and minute strings to form a LocalDateTime object.
     * Ensures hour and minute are zero-padded to two digits.
     * @param hour the hour as a string
     * @param minute the minute as a string
     * @param date the LocalDate object
     * @return the resulting LocalDateTime object
     */
    public static LocalDateTime forgeLocalDateTime(String hour, String minute, LocalDate date) {

        hour = leftpad(hour, 2);
        minute = leftpad(minute, 2);

        final LocalDateTime dateTime = LocalDateTime
                .parse(date + " " + hour + ":" + minute,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return dateTime;
    }

    /**
     * Left-pads the given text with zeros to ensure it reaches the target length.
     * @param text the text to be padded
     * @param targetLength the target length of the padded text
     * @return the padded text
     */
    public static String leftpad(String text, int targetLength) {
        final int padding = targetLength - text.length();
        if (padding > 0) return "0".repeat(padding) + text;
        return text;
    }
}
