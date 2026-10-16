package net.whynotjava.lynel.Parser;

import java.util.ArrayList;
import java.util.HashMap;

import net.whynotjava.lynel.Runtime.Context;
import net.whynotjava.lynel.Runtime.Variable;
import net.whynotjava.lynel.Tokenizer.Tokenizer;

import static net.whynotjava.lynel.Util.ArrayHelp.*;
import static net.whynotjava.lynel.Runtime.Runtime.*;

public class FunctionContainer {
    public String returnType;
    public ArrayList<FunctionParameter> paramters;
    public ArrayList<String> codeTokens;

    public FunctionContainer() {
        codeTokens = new ArrayList<>();
        paramters = new ArrayList<>();
    }

    public static class FunctionParameter {
        public String type;
        public String name;
    }

    public void log(String name) {
        System.out.println("Function '" + name + "':");
        System.out.println("    return type: " + returnType + "");
        System.out.print("    parameters: ");
        for (FunctionParameter p : paramters) {
            System.out.print(p.type + " " + p.name + ", ");
        }
        System.out.print('\n');
        System.out.print("    Tokens: ");
        Tokenizer.logTokens(codeTokens);
    }

    public Object executeFunction(Context ctx) {
        ArrayList<String> tokens = deepCopy(codeTokens);

        while (true) {

            String evalToken = tokens.getFirst();
            tokens.removeFirst();

            // types
            if (inArray(VARIABLE_TYPES, evalToken)) {
                Variable newVar = new Variable(evalToken, tokens.getFirst());
                tokens.removeFirst();

                if (tokens.getFirst().equals(";")) {
                    continue;
                } else {
                    newVar.setValue(Experesion.eval(ctx, tokens, newVar));
                }
            }

            break;
        }

        // java defined functions

        return null;
    }
}
