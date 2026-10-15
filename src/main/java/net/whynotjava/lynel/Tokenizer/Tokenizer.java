package net.whynotjava.lynel.Tokenizer;

import java.text.ParseException;
import java.util.ArrayList;

import net.whynotjava.lynel.Exceptions.ParserException;
import net.whynotjava.lynel.Exceptions.TokenizerException;

public class Tokenizer {
    public static String KEY_CHARS = "abcdefghijklmnopqrstuvwxyz1234567890_";
    public static int MAX_TOKEN_LENGH = 5000;
    // public static int MAX_OPERATOR_LENGH = 3;
    public static String DOUBLED_OPERATORS[] = { "=", "+", "-", "*", "&", "|", ">", "<" };
    public static String SINGLE_OPERATORS[] = { "%", "!", "/" };

    public static ArrayList<String> tokenize(String input) throws TokenizerException, ParserException {
        ArrayList<String> tokens = new ArrayList<>();

        while (input.length() > 0) {
            input = input.trim();
            char typeChar = input.charAt(0);

            Token t = null;

            // System.out.println("'" + input + "' --- end ---");
            // logTokens(tokens);
            if (Character.isAlphabetic(typeChar)) {
                t = parseKeyword(input);

            } else if (typeChar == '{' || typeChar == '}' || typeChar == '(' || typeChar == ')' || typeChar == ';' || typeChar == ',') {
                tokens.add(String.valueOf(typeChar));
                input = input.substring(1, input.length());
            } else if (typeChar == '"' || typeChar == '\'' || typeChar == '`') {
                t = parseString(input);
            } else if (typeChar == '/' && input.charAt(1) == '/') {
                int i = 0;
                while (input.charAt(i) != '\n') {
                    i++;
                }
                input = input.substring(i, input.length());
            } else if (inArray(DOUBLED_OPERATORS, String.valueOf(typeChar))
                    || inArray(SINGLE_OPERATORS, String.valueOf(typeChar))) {
                t = parseOperator(input);
            } else if (Character.isDigit(typeChar)) {
                t = parseNumber(input);
            } else
                throw new TokenizerException("Unknown Token Type: %" + typeChar + "%.");

            if (t != null) {
                tokens.add(t.token);
                input = input.substring(t.offset, input.length());
            }
        }

        return tokens;
    }

    private static Token parseKeyword(String in) throws TokenizerException {
        Token t = new Token();
        t.offset = 0;
        t.token = "";
        while (isKeyChar(in.charAt(t.offset))) {
            if (t.offset > MAX_TOKEN_LENGH)
                throw new TokenizerException("Token is too long.");
            t.token += in.charAt(t.offset);
            t.offset++;
        }
        return t;
    }

    private static Token parseString(String in) {
        char openChar = in.charAt(0);
        Token t = new Token();
        t.token = "" + openChar;
        t.offset = 1;
        while (t.offset < in.length()) {
            // System.out.println("String parser: offset["+t.offset+"], token="+t.token);
            if (in.charAt(t.offset) == openChar) {
                // System.out.println("Potential Exit by current equals openChar.
                // cur="+in.charAt(t.offset)+"; open="+openChar);
                if (in.charAt(t.offset - 1) != '\\')
                    break;
            }
            t.token += in.charAt(t.offset);
            t.offset++;
        }
        t.offset++;
        return t;
    }

    private static Token parseNumber(String in) {
        Token t = new Token();

        while (Character.isDigit(in.charAt(t.offset)) || in.charAt(t.offset) == '-' || in.charAt(t.offset) == '.') {
            if (t.offset == 0 && Character.isAlphabetic(in.charAt(1))) {
                t.token = String.valueOf(in.charAt(0)) + String.valueOf(in.charAt(1));
                t.offset = 2;
                continue;
            }
            t.token += in.charAt(t.offset);
            t.offset++;
        }

        return t;
    }

    private static Token parseOperator(String in) throws TokenizerException, ParserException {
        char firstChar = in.charAt(0);

        String operator = "" + firstChar;

        if (inArray(SINGLE_OPERATORS, operator)) {

        } else if (inArray(DOUBLED_OPERATORS, operator)) {
            // doubled?
            if (in.charAt(1) == firstChar) {
                operator += firstChar;
            }
        } else {
            throw new TokenizerException("'" + firstChar + "'' is not an operator..? Wait is it?");
        }

        // overloaded?
        if (in.charAt(operator.length()) == '=') {
            operator += "=";
        }

        if (operator.equals("++=") || operator.equals("--="))
            throw new ParserException("'" + operator + "' is not valid syntax.");

        Token t = new Token();
        t.offset = operator.length();
        t.token = operator;

        return t;
    }

    private static boolean inArray(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str))
                return true;
        }
        return false;
    }

    private static boolean isKeyChar(char c) {
        if (47 < c && c < 58)
            return true;
        if (64 < c && c < 91)
            return true;
        if (96 < c && c < 123)
            return true;
        if (c == '_')
            return true;
        return false;
    }

    public static void logTokens(ArrayList<String> tokens) {
        System.out.print("[ ");
        for (int i = 0; i < tokens.size(); i++) {
            System.out.print("'" + tokens.get(i) + "'" + (i + 1 < tokens.size() ? "," : "") + " ");
        }
        System.out.println("]");
    }
}
