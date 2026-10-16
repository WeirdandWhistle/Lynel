package net.whynotjava.lynel.Runtime;

public class Variable {
    public String type; 
    public String name;
    public Object value;

    public Variable(String type, String name){
        this.type = type;
        this.name = name;
        setDefaultValue();
    }
    public Variable(String type, String name, Object value){
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public void setDefaultValue(){
        switch (type) {
            case "int":
                value = 0;
                break;
            case "double":
                value = 0.0;
                break;
            case "float":
                value = 0.0f;
                break;
            case "boolean":
                value = false;
                break;
            case "String":
                value = "";
                break;
            case "char":
                value = (char) 0;
                break;        
            default:
                value = null;
                break;
        }
    }
}
