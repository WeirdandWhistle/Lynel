package net.whynotjava.lynel.Runtime;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.whynotjava.lynel.Parser.FunctionContainer;
import net.whynotjava.lynel.Parser.FunctionFinder;
import net.whynotjava.lynel.Tokenizer.Tokenizer;

public class Runtime {
    public static int run() {
        try {

            String codeString = new String(Files.readAllBytes(Paths.get("code.lyn")), StandardCharsets.UTF_8);
            ArrayList<String> tokens = Tokenizer.tokenize(codeString);

            // Tokenizer.logTokens(tokens);

            HashMap<String, FunctionContainer> functions = FunctionFinder.filterFunctions(tokens);

            for(Map.Entry<String, FunctionContainer> i : functions.entrySet()){
                i.getValue().log(i.getKey());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
