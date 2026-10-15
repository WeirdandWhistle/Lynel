package net.whynotjava.lynel.Parser;

import java.util.ArrayList;
import java.util.HashMap;

import net.whynotjava.lynel.Exceptions.ParserException;
import net.whynotjava.lynel.Parser.FunctionContainer.FunctionParameter;

public class FunctionFinder {
    public static HashMap<String, FunctionContainer> filterFunctions(ArrayList<String> tokens) throws ParserException{
        HashMap<String, FunctionContainer> functions = new HashMap<>();

        while(true){
            FunctionContainer fun = new FunctionContainer();

            fun.returnType = tokens.getFirst();
            tokens.removeFirst();
            String functionName = tokens.getFirst();
            tokens.removeFirst();

            // expect opening '('
            if(!tokens.getFirst().equals("(")){
                throw new ParserException("Unexpected token:"+tokens.getFirst()+"expected: '('");
            }
            tokens.removeFirst();

            while(!tokens.getFirst().equals(")")){
                FunctionParameter parameter = new FunctionParameter();

                parameter.type = tokens.getFirst();
                tokens.removeFirst();
                parameter.name = tokens.getFirst();
                tokens.removeFirst();

                if(!tokens.getFirst().equals(")")){
                    if(!tokens.getFirst().equals(",")) throw ParserException.expectedButGot(",", tokens.getFirst());
                    tokens.removeFirst();
                }

                fun.paramters.add(parameter);
            }
            tokens.removeFirst();

            if(!tokens.getFirst().equals("{")){
                throw ParserException.expectedButGot("{", tokens.getFirst());
            }
            tokens.removeFirst();

            int bracesIn = 1;
            while(bracesIn > 0){
                // System.out.println("Braces: "+bracesIn+" Token: "+tokens.getFirst());
                if(tokens.getFirst().equals("{")) bracesIn++;
                else if(tokens.getFirst().equals("}")) bracesIn--;
                
                if(bracesIn != 0) {
                    fun.codeTokens.add(tokens.getFirst());
                }
                tokens.removeFirst();
            }

            functions.put(functionName, fun);

            if(tokens.size() <= 0) break;
        }
        return functions;
    }
}
