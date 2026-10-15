package net.whynotjava.lynel.Runtime;

import java.util.ArrayList;
import java.util.HashMap;

import net.whynotjava.lynel.Exceptions.ParserException;
import net.whynotjava.lynel.Parser.FunctionContainer;
import static net.whynotjava.lynel.Util.ArrayHelp.*;
import static net.whynotjava.lynel.Util.NumberHelp.*;

public class Experesion {

    public static class Value {
        public String type;
        public Object value;
        
        public Value(){

        }
        public Value(String type, Object value){
            this.type = type;
            this.value = value;
        }
    }

    public static Value eval(Context ctx, ArrayList<String> tokens, Variable variable) throws ParserException {

        String t = tokens.getFirst();
        tokens.removeFirst();

        if (t.equals("++")) {
            if (!isNumberType(variable.type))
                throw new ParserException("Must be a number type to increment.");
            return new Value(variable.type, addNumber(variable, 1));
        } else if (t.equals("++")) {
            if (!isNumberType(variable.type))
                throw new ParserException("Must be a number type to decrement.");
            return new Value(variable.type, addNumber(variable, -1));
        } else if (t.equals("=")){
            ArrayList<String> newTokens = new ArrayList<>();
            for()
        }

        return null;
    }
}
