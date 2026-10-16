package net.whynotjava.lynel.Util;

import java.util.ArrayList;

import net.whynotjava.lynel.Exceptions.ParserException;

public class TypeHelp {
    public static String getTokenType(ArrayList<String> tokens) throws ParserException {
        String t = tokens.getFirst().toLowerCase();
        if (t.charAt(0) == '"' || t.charAt(0) == '\'' || t.charAt(0) == '`')
            return "String";
        else if (Character.isDigit(t.charAt(0))) {
            // number of some type...
            if (t.endsWith("f"))
                return "float";
            if (t.endsWith("l"))
                return "long";
            if (t.endsWith("d"))
                return "double";
            if (t.startsWith("0x"))
                return "int";
            if (t.startsWith("0b"))
                return "int";
            if (t.contains(".") || t.contains("e"))
                return "double";

            Long scaleCheck = Long.valueOf(t.replaceAll("-", ""));
            if(scaleCheck > 2E9) return "long";
            
            return "int";

        } else if (Character.isAlphabetic(t.charAt(0))) {
            if (tokens.size() > 2 && tokens.get(1).equals("("))
                return "function";
            else
                return "variable";
        }
        throw new ParserException("Token must have a type: " + t);
    }
}
