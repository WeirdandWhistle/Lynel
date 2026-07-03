package net.whynotjava.lynel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.*;

import net.whynotjava.lynel.Function;

public class Interpreter {

    private String code;

    private Map<String, Function> functionMap = new HashMap<>();

    public Interpreter(String fileName){
        if(fileName == null) throw new IllegalArgumentException("File name can not be null");
        try {
            code = new BufferedReader(new FileReader(new File(fileName))).readAllAsString();
            code.trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int run(){
        try{

           preScan();

           Function.logFunctionMapTree(functionMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        return 1;
    }

    private boolean preScan(){
        String sub = code;
        while(sub.length() != 0){
            // System.out.println("sub: '"+sub+"'");
            Function f = Function.scanFunction(sub);
            sub = sub.substring(f.endIndex+1).trim();
            functionMap.put(f.name, f);
        }
        return true;
    }
}


