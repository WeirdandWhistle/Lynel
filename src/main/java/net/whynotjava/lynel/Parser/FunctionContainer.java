package net.whynotjava.lynel.Parser;

import java.util.ArrayList;

import net.whynotjava.lynel.Tokenizer.Tokenizer;

public class FunctionContainer {
    public String returnType;
    public ArrayList<FunctionParameter> paramters;
    public ArrayList<String> codeTokens;

    public FunctionContainer(){
        codeTokens = new ArrayList<>();
        paramters = new ArrayList<>();
    }

    public static class FunctionParameter {
        public String type;
        public String name;        
    }

    public void log(String name){
        System.out.println("Function '"+name+"':");
        System.out.println("    return type: "+returnType+"");
        System.out.print("    parameters: ");
        for(FunctionParameter p : paramters){
            System.out.print(p.type + " "+p.name+", ");
        }
        System.out.print('\n');
        System.out.print("    Tokens: ");
        Tokenizer.logTokens(codeTokens);
    }
}
