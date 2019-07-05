package de.sveri.joc.interpreter;

import com.github.javaparser.ast.CompilationUnit;
import de.sveri.joc.parser.Main;

import java.io.IOException;
import java.io.InputStream;

public class Interpreter {

    public static String fromRepl(String input){
        return "";
    }

    public static String fromIdea(String fileName, InputStream is) throws IOException {
        CompilationUnit compilationUnit = Main.parseIs(fileName, is);

        return compilationUnit.toString();
    }
}
