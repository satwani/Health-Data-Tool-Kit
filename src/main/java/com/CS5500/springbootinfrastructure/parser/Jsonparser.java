package com.CS5500.springbootinfrastructure.parser;

import com.CS5500.springbootinfrastructure.dao.DateLog;
import com.CS5500.springbootinfrastructure.dao.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import java.io.*;

public class Jsonparser {
    public static DateLog[] getPOJO() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
                .builder()
                .allowIfBaseType(Type.class)
                .build();


        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        DateLog[] dates = new ObjectMapper().readerFor(DateLog[].class).readValue(new File("src//main//resources//storyline.json"));

        return dates;

    }
}
