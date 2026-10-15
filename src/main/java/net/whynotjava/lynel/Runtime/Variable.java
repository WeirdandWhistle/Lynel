package net.whynotjava.lynel.Runtime;

import net.whynotjava.lynel.Runtime.Experesion.Value;

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

    public void setValue(Value val){
        switch (this.type) {
            case "int":
                value = (Integer) val.value;
                break;
            case "double":
                value = (Double) val.value;
                break;
            case "float":
                value = (Float) val.value;
                break;
            case "boolean":
                value = (Boolean) val.value;
                break;
            case "char":
                value = (Character) val.value;
                break;
            case "String":
                value = (String) val.value;
                break;
        
            default:
                value = null;
                break;
        }
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
