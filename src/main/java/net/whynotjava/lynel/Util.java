package net.whynotjava.lynel;
import java.util.*;

import javax.management.RuntimeErrorException;

import net.whynotjava.lynel.Variable;

public class Util {
    public static String charsThatStartAString = "`'\"";
    public static int findNext(int start, String s, char c){
        for(int j = 0; j <s.length();j++){
            // System.out.println(s.charAt(j) + " - " + (s.charAt(j)==c));
            if(s.charAt(j)==c) return j;
        }
        throw new IllegalArgumentException("No char: '" + c + "'");
    }
    public static int findNextNoneLetter(int start, String s){

        for(int i = start; i <s.length();i++){
            if(!Character.isLetter(s.charAt(i))) return i;
        }

        throw new IllegalArgumentException("No none letter.");
    }
    public static int numOf(String s, char c){
        int count = 0;
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == c) count++;
        }
        return count;
    }
    public static List<Variable> parseParams(String code){
        int start = findNext(0, code, '(');
        int end = findNext(start, code, ')');

        String paramString = code.substring(start+1, end).trim();
        if(paramString.length() > 0){
            String vars[] = paramString.split(",");
            List<Variable> out = new ArrayList<>();
            for(String var : vars){
                out.add(Variable.parseFunctionVarible(var));
            }
            return out;
        }
        return null;
    }
    public static boolean toggle(boolean a){
        return a ? false : true;
    }
    public static int parseCurlyBraceBlock(String code){
        if(code.charAt(0) != '{') throw new IllegalArgumentException("Curly brace code block must start with a '{'.");

        boolean inString = false;
        char closeStringChar = 0;
        int nestDepth = 0;
        char prevChar = 0;
        for(int i = 0; i<code.length();i++){
            if(!inString){
                if(code.charAt(i) == '{'){
                    nestDepth++;
                }
                else if(code.charAt(i) == '}'){
                    nestDepth--;
                }
            }
            if(prevChar != '\\' && charsThatStartAString.contains(new String(new char[]{code.charAt(i)}))){
                if(code.charAt(i) == closeStringChar && inString){
                    inString = false;
                    closeStringChar = 0;
                }                
                else if(!inString){
                    inString = true;
                    closeStringChar = code.charAt(i);
                }
            }
            if(nestDepth == 0){
                return i;
            }
            prevChar = code.charAt(i);
        }
        throw new IllegalArgumentException("No ending curly brace!");
    }
}
