package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    private final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
    private final SimpleDateFormat mSimpleDateTimeFormat = new SimpleDateFormat(DATE_TIME_PATTERN);

    public DateDeserializer() {
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Date date = null;

        try {
            date = mSimpleDateTimeFormat.parse(p.getText());
        } catch (ParseException e) {

        }

        if (date == null) {
            try {
                date = mSimpleDateFormat.parse(p.getText());
            } catch (ParseException e1) {

            }
        }
        return date;
    }
}