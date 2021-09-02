package de.yy18.nettyserver.server.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateParser {

    public static String parseDate(final long milliseconds) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return simpleDateFormat.format(new Date(milliseconds));
    }

    public static String parseTime(final long milliseconds) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        return simpleDateFormat.format(new Date(milliseconds));
    }

}
