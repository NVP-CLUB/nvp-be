package nvp_api.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    // String -> LocalDate로 변환
    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, YYYYMMDD_FORMATTER);
    }

}
