package com.my.cinema.booking.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$";
    private static final String CORRECT_PASSWORD = "[a-zA-Z0-9]{4,20}";
    private static final String CORRECT_NAME = "[a-zA-Z\\p{IsCyrillic}]{3,20}";

    private Validator() {
    }

    private static boolean isCorrectEmail(String email) {
        Pattern VALID_EMAIL_REG =
                Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_REG.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidPassword(String password1, String password2) {
        Pattern validPassword = Pattern.compile(CORRECT_PASSWORD);
        Matcher matcher = validPassword.matcher(password1);
        return isSamePassword(password1, password2) && matcher.matches();
    }

    private static boolean isSamePassword(String password1, String password2) {
        return password1.equals(password2);
    }

    private static boolean isCorrectName(String name) {
        Pattern VALID_NAME_REG =
                Pattern.compile(CORRECT_NAME);
        Matcher matcher = VALID_NAME_REG.matcher(name);
        return matcher.matches();
    }


    public static boolean notValidData(String name, String email, String password, String repeatPassword) {
        return !(isCorrectName(name) && isCorrectEmail(email) && isValidPassword(password, repeatPassword));
    }
}
