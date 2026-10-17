package net.whynotjava.lynel.Runtime;

import java.util.ArrayList;
import java.util.HashMap;

import net.whynotjava.lynel.Parser.FunctionContainer;
import net.whynotjava.lynel.Statements.Statement;

public class Context {
    HashMap<String, FunctionContainer> functions;
    HashMap<String, Variable> variables;

    public Context(HashMap<String, FunctionContainer> functions,
            HashMap<String, Variable> variables) {
        this.functions = functions;
        if (variables != null)
            this.variables = variables;
        else
            this.variables = new HashMap<>();
    }
}
