package infra.general_utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static LocalDateTime parseDate(String pattern, String dateToParse) {
        LocalDateTime res = null;

        if (dateToParse == null) {
            return null;

        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                res = LocalDateTime.from(formatter.parse(dateToParse));
            } catch (Exception e) {

            } finally {
                return res;
            }
        }
    }
}
