package net.whynotjava.lynel;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import net.whynotjava.lynel.Tokenizer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start program\n--------------");
        try {

            String codeString = new String(Files.readAllBytes(Paths.get("code.lyn")), StandardCharsets.UTF_8);
            ArrayList<String> tokens = Tokenizer.tokenize(codeString);

            Tokenizer.logTokens(tokens);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--------------\nStop program");
    }
}