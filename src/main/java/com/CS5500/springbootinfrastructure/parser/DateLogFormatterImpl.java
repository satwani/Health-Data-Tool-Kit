package com.CS5500.springbootinfrastructure.parser;

import javax.xml.crypto.Data;
import java.sql.Timestamp;

public class DateLogFormatterImpl implements DataFormatter {
    @Override
    public Timestamp convertTimestamp(String unformattedDate) {
        // "20130209T181453-0800"
        return new DataFormatterImpl().convertTimestamp(unformattedDate + "T000000");
    }
}
