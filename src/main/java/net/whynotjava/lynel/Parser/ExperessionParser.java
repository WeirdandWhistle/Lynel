package net.whynotjava.lynel.Parser;

import java.util.ArrayList;

import net.whynotjava.lynel.Exceptions.ParserException;
import net.whynotjava.lynel.Util.TypeHelp;

public class ExperessionParser {

    public static ArrayList<String> findStatement(ArrayList<String> tokens) {
        ArrayList<String> expressionTokens = new ArrayList<>();
        while (!tokens.getFirst().equals(";")) {
            expressionTokens.add(tokens.getFirst());
            tokens.removeFirst();
        }
        tokens.removeFirst();
        return expressionTokens;
    }

    public static ParseTree parseTree(ArrayList<String> tokens) throws ParserException {
        if (tokens.getFirst().equals("="))
            tokens.removeFirst();

        ParseTree root = null;

        while (tokens.size() > 0) {

            if (root == null) {
                boolean negateAtom1 = false;
                if (tokens.getFirst().equals("-")) {
                    negateAtom1 = true;
                    tokens.removeFirst();
                }

                ParseTree atom1 = null;
                if (tokens.getFirst().equals("(")) {
                    ArrayList<String> parenthesesTokens = findParentheses(tokens);
                    if (negateAtom1) {
                        atom1 = new ParseTree(new ParseTree(parseTree(parenthesesTokens), null, "negate"), null,
                                "parentheses");
                    } else {
                        atom1 = new ParseTree(parseTree(parenthesesTokens), null, "parentheses");
                    }
                } else {
                    String atom1String = (negateAtom1 ? "-" : "") + tokens.getFirst();
                    tokens.removeFirst();
                    atom1 = new ParseTree(null, null, atom1String);
                }

                String operator = tokens.getFirst();

                boolean negateAtom2 = false;
                if (tokens.getFirst().equals("-")) {
                    negateAtom2 = true;
                    tokens.removeFirst();
                }
                ParseTree atom2 = null;
                if (tokens.getFirst().equals("(")) {
                    ArrayList<String> parenthesesTokens = findParentheses(tokens);
                    if (negateAtom2) {
                        atom2 = new ParseTree(new ParseTree(parseTree(parenthesesTokens), null, "negate"), null,
                                "parentheses");
                    } else {
                        atom2 = new ParseTree(parseTree(parenthesesTokens), null, "parentheses");
                    }
                } else {
                    String atom2String = (negateAtom2 ? "-" : "") + tokens.getFirst();
                    tokens.removeFirst();
                    atom2 = new ParseTree(null, null, atom2String);
                }

                root = new ParseTree(atom1, atom2, operator);
            } else {
                String operator = tokens.getFirst();
                tokens.removeFirst();

                boolean negateAtom = false;
                if (tokens.getFirst().equals("-")) {
                    negateAtom = true;
                    tokens.removeFirst();
                }

                ParseTree atom = null;
                if (tokens.getFirst().equals("(")) {
                    ArrayList<String> parenthesesTokens = findParentheses(tokens);
                    if (negateAtom) {
                        atom = new ParseTree(new ParseTree(parseTree(parenthesesTokens), null, "negate"), null,
                                "parentheses");
                    } else {
                        atom = new ParseTree(parseTree(parenthesesTokens), null, "parentheses");
                    }
                } else {
                    String atomString = (negateAtom ? "-" : "") + tokens.getFirst();
                    tokens.removeFirst();
                    atom = new ParseTree(null, null, atomString);
                }

                root = new ParseTree(root, atom, operator);
            }
        }
        return orderParseTree(root);
    }

    public static ParseTree orderParseTree(ParseTree root) throws ParserException {

        // standard unorderd struct
        //        operator
        //        /      \
        //   operator    value
        //    /    \
        //  value  value

        if (root == null || root.value.equals("parentheses")) {
            return root;
        }

        root.right = orderParseTree(root.right);
        root.left = orderParseTree(root.left);

        if (root.left != null && root.left.right != null) { // this checks the left tree existing and that the left tree
                                                            // is an operator by the fact that values (or stopping
                                                            // points) have the right tree set to null
            if (getOperatorPrecedenceLevel(root.value) > getOperatorPrecedenceLevel(root.left.value)) { // greater
                                                                                                        // precendece
                                                                                                        // sink to the
                                                                                                        // bottom
                ParseTree tmp1 = root.left;
                root.left = tmp1.right;

                tmp1.right = root;
                root = orderParseTree(tmp1);
                return root;
            }
        } else if (root.right != null && root.right.right != null) {
            if (getOperatorPrecedenceLevel(root.value) > getOperatorPrecedenceLevel(root.right.value)) { // greater
                                                                                                        // precendece
                                                                                                        // sink to the
                                                                                                        // bottom
                ParseTree tmp1 = root.right;
                root.right = tmp1.left;

                tmp1.left = root;
                root = orderParseTree(tmp1);
                return root;
            }
        }
        return root;
    }

    public static int getOperatorPrecedenceLevel(String o) throws ParserException {
        switch (o) {
            case "parentheses":
            case "negate":
                return 255;
            case "**":
                return 100;
            case "*":
                return 95;
            case "/":
                return 95;
            case "%":
                return 95;
            case "+":
                return 90;
            case "-":
                return 90;
            case "&":
                return 85;
            case "|":
                return 85;
            case ">>":
                return 85;
            case "<<":
                return 85;
            case "^":
                return 85;
            case "&&":
                return 75;
            case "||":
                return 75;
            case "==":
                return 75;
            case "!=":
                return 75;
            default:
                throw new ParserException("% " + o + " % is not an operator.");
        }
    }

    public static ArrayList<String> findParentheses(ArrayList<String> tokens) {
        int depth = 1;
        ArrayList<String> out = new ArrayList<>();
        while (depth > 0) {
            if (tokens.getFirst().equals("("))
                depth++;
            if (tokens.getFirst().equals(")"))
                depth--;
            out.add(tokens.getFirst());
        }
        out.removeLast();
        return out;
    }
}
