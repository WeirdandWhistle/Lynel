package net.whynotjava.lynel;

public class Variable {
    public String type;
    public String name;
    public Object value;

    public Variable(String type, String name){
        this.type = type;
        this.name = name;
    }
    public Variable(String type, String name, Object value){
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public static Variable parseFunctionVarible(String s){
        s.trim();
        String arr[] = s.split(" ");
        if(arr.length != 2) throw new IllegalArgumentException("Cant parse varible with not length 2.");
        return new Variable(arr[0], arr[1]);
    }
}
