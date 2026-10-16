package net.whynotjava.lynel.Parser;

public class ParseTree {
    public ParseTree left;
    public ParseTree right;
    public String value;
    
    public ParseTree(ParseTree left, ParseTree right, String value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
    
}
