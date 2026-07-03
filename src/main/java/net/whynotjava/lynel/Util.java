package net.whynotjava.lynel;
import java.util.*;

import javax.management.RuntimeErrorException;

import net.whynotjava.lynel.Variable;

public class Util {
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
}
