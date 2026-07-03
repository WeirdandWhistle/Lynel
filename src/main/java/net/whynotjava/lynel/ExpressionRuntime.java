package net.whynotjava.lynel;

import net.whynotjava.lynel.Function;
import net.whynotjava.lynel.Variable;
import static net.whynotjava.lynel.Util.*;

import java.util.Map;

public class ExpressionRuntime {

    public static void integerExpression(String expression, Map<String, Function> functionMap, Map<String, Variable> variableMap){
        int cursor = 0;
        int next = 0;

        char c = expression.charAt(cursor); 
        while(c == ' '){
            cursor++;
        }
        next = cursor;
        c = expression.charAt(next);
        while(c == '_' || Character.isAlphabetic(c) || Character.isDigit(c)){
            next++;
            c = expression.charAt(cursor);
        }
        String element = expression.substring(cursor, next);
        if(variableMap.get(element) != null){

        }
    }

}
