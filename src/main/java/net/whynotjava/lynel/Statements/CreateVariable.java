package net.whynotjava.lynel.Statements;

import java.util.ArrayList;

import net.whynotjava.lynel.Parser.ExperessionParser;
import net.whynotjava.lynel.Parser.ParseTree;
import net.whynotjava.lynel.Runtime.Context;

public class CreateVariable implements Statement {

    public String type;
    public String name;
    private ParseTree treeRoot;

    public CreateVariable(String type, String name, ArrayList<String> tokens){
        this.type = type;
        this.name = name;
        treeRoot = ExperessionParser.parseTree(ExperessionParser.findStatement(tokens));
    }

    @Override
    public void execute(Context ctx) {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
