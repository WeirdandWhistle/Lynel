package net.whynotjava.lynel.IntegerParser;

import java.lang.Character;

public class IntegerParser {

    public String e;

    public IntegerParser(String e){
        this.e = e.trim(); 
    }

    public void log(String s){
        System.out.println("> " + s);
    }

    public int run(){
        log(e);

        int cursor = 0;
        int next = -1;

        char c = '_';
        while(c == '_' || Character.isDigit(c) || Character.isAlphabetic(c)){
            next++;
            c = e.charAt(next);
        }

        log("1: "+ e.substring(cursor, next));


        return -1;
    }
    
}
