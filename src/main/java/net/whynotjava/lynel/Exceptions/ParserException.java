package net.whynotjava.lynel.Exceptions;

public class ParserException extends Exception {
    public ParserException(String m) {
        super(m);
    }

    public static ParserException expectedButGot(String expected, String got) {
        return new ParserException("Expected '" + expected + "'. But got " + got + " instead.");
    }
}
