package com.ozenero.adscategorizedservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Pattern;

public class Utils {
    public static boolean containsWord(String text, String word) {
        return Pattern.compile("\\b" + word + "\\b").matcher(text).find();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
