package net.whynotjava.lynel.Util;

import net.whynotjava.lynel.Exceptions.ParserException;
import net.whynotjava.lynel.Runtime.Variable;

public class NumberHelp {
    public static boolean isNumberType(String type) {
        return (type.equals("int") || type.equals("double") || type.equals("float") || type.equals("short")
                || type.equals("long"));
    }

    public static Object addNumber(Variable v, Number num) throws ParserException {
        switch (v.type) {
            case "int":
                return ((Number) v.value).intValue() + num.intValue();
            case "double":
                return ((Number) v.value).doubleValue() + num.doubleValue();
            case "float":
                return ((Number) v.value).floatValue() + num.floatValue();
            case "short":
                return (((Number) v.value).shortValue() + num.shortValue());
            case "long":
                return ((Number) v.value).longValue() + num.longValue();

            default:
                throw new ParserException("Can not add a non-number value.");
        }
    }
}
