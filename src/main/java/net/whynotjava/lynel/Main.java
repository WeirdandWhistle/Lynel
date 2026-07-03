package net.whynotjava.lynel;

import net.whynotjava.lynel.Interpreter;
import net.whynotjava.lynel.IntegerParser.IntegerParser;

public class Main{
    public static void main(String[] args){
        System.out.println("Start program\n--------------");

        IntegerParser ip = new IntegerParser("1 + 1");

        System.out.println("out: " + ip.run());

        // Interpreter i = new Interpreter("code.lyn");
        // i.run();
    }
}