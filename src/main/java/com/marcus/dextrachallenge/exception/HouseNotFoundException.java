package com.marcus.dextrachallenge.exception;

public class HouseNotFoundException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    public final String id;

    public HouseNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }
}
