package com.kameronbray.exceptions;


/**
 * Kameron Bray (2015) - Throws an exception for invalid input.
 *
 * @author Kameron Bray
 */
public class UnexpectedInputException extends RuntimeException {

    public UnexpectedInputException(final String string) {
        super(string);
    }

}
