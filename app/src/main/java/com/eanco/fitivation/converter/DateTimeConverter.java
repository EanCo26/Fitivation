package com.eanco.fitivation.converter;

import static com.eanco.fitivation.util.Constants.TIMESTAMP_PATTERN;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeConverter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN, Locale.UK);

    public static String milliToDatetimeStr(@NonNull long timeMilli) {
        return sdf.format(new Date(timeMilli));
    }
}
