package de.sveri.joc.antlr;

public class Utils {

    public static String getClassName(String fileName, String methodName){
        return capitalize(fileName) + capitalize(methodName);
    }

    static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
