package com.CS5500.springbootinfrastructure.view;

import com.CS5500.springbootinfrastructure.parser.DataFormatterImpl;

import java.sql.Timestamp;

public class DateLogQuery {

    public static void main(String[] args) {


        Timestamp t = new DataFormatterImpl().convertTimestamp("20130209T185023-0800");
        System.out.println(t.toString());
    }
}
