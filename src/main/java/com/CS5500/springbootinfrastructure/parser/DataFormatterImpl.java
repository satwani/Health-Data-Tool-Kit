package com.CS5500.springbootinfrastructure.parser;

import java.sql.Timestamp;

public class DataFormatterImpl implements DataFormatter {
    @Override
    public Timestamp convertTimestamp(String unformattedDate) {
        // "20130209T181453-0800"
        String yyyy = unformattedDate.substring(0, 4);
        String mm = unformattedDate.substring(4, 6);
        String dd = unformattedDate.substring(6, 8);
        String hh = unformattedDate.substring(9, 11);
        String min = unformattedDate.substring(11, 13);
        String ss = unformattedDate.substring(13, 15);

        String jdbcFormat = yyyy + "-" + mm + "-" + dd + " " + hh + ":" + min +
                ":" + ss;

        return Timestamp.valueOf(jdbcFormat);
    }
}
