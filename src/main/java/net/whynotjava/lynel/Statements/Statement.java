package net.whynotjava.lynel.Statements;

import net.whynotjava.lynel.Runtime.Context;

@FunctionalInterface 
public interface Statement {
    void execute(Context ctx);
}
