package net.whynotjava.lynel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.whynotjava.lynel.Variable;
import static net.whynotjava.lynel.Util.*;

public class Function {

    public String name;
    public String code;
    public String returnType;
    public List<Variable> params;
    public int endIndex;

    public static Function scanFunction(String code){
        Function f = new Function();

        int cursor = 0;

        int next = findNext(cursor, code, ' ');
        f.returnType = code.substring(cursor, next);
        cursor = next+1;
        // System.out.println("return type '"+f.returnType+"'" + " next "+next);

        next = findNextNoneVariableNameChar(cursor, code);
        f.name = code.substring(cursor, next);
        cursor = findNext(next, code, '(');
        // System.out.println("name '"+f.name+"'");

        next = findNext(cursor, code, ')')+1;
        f.params = parseParams(code.substring(cursor, next));
        // System.out.println("param peice '"+code.substring(cursor, next)+"'");
        cursor = next;

        int start = findNext(cursor, code, '{');
        int end = findFunctionCodeBlock(code.substring(start))+start;

        f.code = code.substring(start+1, end).trim();
        f.endIndex = end;
        return f;
    }

    public static int findFunctionCodeBlock(String code){
        System.out.println("findFunctionCodeBlock: '"+code+"'");
        return parseCurlyBraceBlock(code);
    }

    // logging
    public static void logFunctionMapTree(Map<String, Function> map){
        for(Map.Entry<String,Function> entry : map.entrySet()){
            Function f =  entry.getValue();

            System.out.println("Function:\n    Name: "+f.name+"\n    Return Value: "+f.returnType+"\n    CodeBlock: "+f.code);
        }
    }
}
