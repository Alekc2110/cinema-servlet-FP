package com.my.cinema.booking.utils;

import com.my.cinema.booking.controller.command.common.RegistrationCommand;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$";
    private static final String CORRECT_PASSWORD = "[a-zA-Z0-9]{4,20}";
    private static final String CORRECT_NAME = "[a-zA-Z\\p{IsCyrillic}]{3,20}";
    private static final String CORRECT_TIME_STAMP = "^[0-9]{4}-[0-9]{2}-[0-9]{2}\\s+[0-9]{2}:[0-9]{2}$";
    private static final String CORRECT_PRICE = "^[0-9]{1,3}$";

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

    public static boolean isCorrectTimeStamp(String timeStamp) {
        Pattern VALID_NAME_REG =
                Pattern.compile(CORRECT_TIME_STAMP);
        Matcher matcher = VALID_NAME_REG.matcher(timeStamp);
        boolean matches = matcher.matches();
        if(matches){
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                final LocalDateTime parse = LocalDateTime.parse(timeStamp, formatter);
                return true;
            } catch (DateTimeParseException e){
                return false;
            }
        }
        return false;
    }

    public static boolean isCorrectPrice(String price) {
        Pattern VALID_NAME_REG =
                Pattern.compile(CORRECT_PRICE);
        Matcher matcher = VALID_NAME_REG.matcher(price);
        return matcher.matches();
    }

}
