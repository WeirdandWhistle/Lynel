package net.whynotjava.lynel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.whynotjava.lynel.Function;
import net.whynotjava.lynel.Variable;
import static net.whynotjava.lynel.Util.*;

public class FunctionRuntime {
    Function function;
    Map<String, Function> functionMap;

    public FunctionRuntime(Function f, Map<String, Function> functionMap){
        this.function = f;
        this.functionMap = functionMap;
    }

    public Object call(List<Variable> params){
        if(params.size() != function.params.size())
             throw new IllegalArgumentException("Number of parameters passed does not match the number of needed parameters.");
        return null;
    }
}
