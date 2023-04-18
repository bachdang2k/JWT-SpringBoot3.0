package com.spring.JWTSecurity.util;

import lombok.Builder;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
public class ValidateUtils {

    private final String ONLY_NUMBER = "[0-9]+";

    private final String USERNAME_PATTERN = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";

    private final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    private Object value;
    private boolean required;
    private Integer maxLength;
    private Integer minLength;
    private String fieldName;
    private String regex;
    private boolean onlyNumber;
    private boolean isInteger;
    private boolean isEmail;
    private boolean isUsername;
    private boolean isPassword;
    private Long max;
    private Long min;

    public Map<String, String> validate() {

        Map<String, String> errors =new HashMap<>();

        //check field is required
        if (required && ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            errors.put(fieldName, fieldName + " is required");
        }

        //check max length of field
        if (!ObjectUtils.isEmpty(maxLength)
            && !ObjectUtils.isEmpty(fieldName)
            && !ObjectUtils.isEmpty(value)
                && value.toString().length() > maxLength
        ) {
            errors.put(fieldName, fieldName + " must have 0 - " + maxLength + " character");
        }

        //check min length of field
        if (!ObjectUtils.isEmpty(minLength)
                && !ObjectUtils.isEmpty(fieldName)
                && !ObjectUtils.isEmpty(value)
                && value.toString().length() < minLength
        ) {
            errors.put(fieldName, fieldName + " must have minimum - " + minLength + " character");
        }

        //check field name is only number
        if (onlyNumber && !ObjectUtils.isEmpty(fieldName) && !ObjectUtils.isEmpty(value)) {
            Pattern patternOnlyNumber = Pattern.compile(ONLY_NUMBER);
            Matcher matcher = patternOnlyNumber.matcher(value.toString());
            if (!matcher.matches()) {
                errors.put(fieldName, fieldName + " must contain only number");
            }
        }

        //check field name is Integer
        if (isInteger && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            try {
                Integer.parseInt(value.toString());
            } catch (Exception exception) {
                errors.put(fieldName, fieldName + " must is Integer number");
            }
        }

        //check range
        if (!ObjectUtils.isEmpty(max)
                && ObjectUtils.isEmpty(min)
                && !ObjectUtils.isEmpty(value)
                && !ObjectUtils.isEmpty(fieldName)) {
            try {
                double valueDouble = Double.parseDouble(value.toString());
                if (valueDouble < min || valueDouble > max) {
                    errors.put(fieldName, fieldName + " must range from " + min + " to " + max);
                }
            } catch (Exception exception) {
                errors.put(fieldName, fieldName + " is invalid data type");
            }
        }

        //check email
        if (isEmail && !ObjectUtils.isEmpty(fieldName) && !ObjectUtils.isEmpty(value)){
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {
                errors.put(fieldName, fieldName + " must be email pattern abc@mail.com");
            }
        }

        if (isUsername && !ObjectUtils.isEmpty(fieldName) && !ObjectUtils.isEmpty(value)){
            Pattern pattern = Pattern.compile(USERNAME_PATTERN);
            Matcher matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {
                errors.put(fieldName, fieldName + " must be username pattern");
            }
        }

        //validate password
        if (isPassword && !ObjectUtils.isEmpty(fieldName) && !ObjectUtils.isEmpty(value)) {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {
                errors.put(fieldName, fieldName + " must Minimum eight characters, at least one uppercase letter, one lowercase letter and one number");
            }
        }

        return errors;
    }

}