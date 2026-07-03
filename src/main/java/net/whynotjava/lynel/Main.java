package net.whynotjava.lynel;

import net.whynotjava.lynel.Interpreter;

public class Main{
    public static void main(String[] args){
        System.out.println("Start program");

        Interpreter i = new Interpreter("code.lyn");
        i.run();
    }
}