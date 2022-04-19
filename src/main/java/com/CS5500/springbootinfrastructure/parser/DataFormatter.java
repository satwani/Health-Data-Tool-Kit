package com.CS5500.springbootinfrastructure.parser;

import java.sql.Timestamp;

public interface DataFormatter {
    Timestamp convertTimestamp(String unformattedDate);
}
