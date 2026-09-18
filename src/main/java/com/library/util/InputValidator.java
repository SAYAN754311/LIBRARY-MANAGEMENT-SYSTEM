package com.library.util;
public final class InputValidator {
    private InputValidator(){}
    public static String required(String value,String field){if(value==null||value.isBlank())throw new IllegalArgumentException(field+" is required.");return value.trim();}
    public static int positiveInt(String value,String field){try{int n=Integer.parseInt(value.trim());if(n<1)throw new NumberFormatException();return n;}catch(NumberFormatException e){throw new IllegalArgumentException(field+" must be a positive number.");}}
    public static int nonNegativeInt(String value,String field){try{int n=Integer.parseInt(value.trim());if(n<0)throw new NumberFormatException();return n;}catch(NumberFormatException e){throw new IllegalArgumentException(field+" must be zero or greater.");}}
}