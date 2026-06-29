package com.wipro.renewal.util;
public class InvalidInputException extends Exception {
    private static final long serialVersionUID = 1L;
    @Override
    public String toString() {
        return "Invalid Input";
    }
}