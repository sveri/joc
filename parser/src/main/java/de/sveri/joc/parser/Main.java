package de.sveri.joc.parser;

import com.github.javaparser.ast.CompilationUnit;
import de.sveri.joc.parser.antlr.CompleteVisitor;
import de.sveri.joc.antlr.generated.JocLexer;
import de.sveri.joc.antlr.generated.JocParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static CompilationUnit parseIs(String fileName, InputStream input) throws IOException {
        CommonTokenStream tokens = getTokens(CharStreams.fromStream(input));
        return parseTokens(fileName, tokens);
    }

    public static CompilationUnit parseString(String fileName, String input) {
        CommonTokenStream tokens = getTokens(CharStreams.fromString(input));
        return parseTokens(fileName, tokens);
    }

    private static CompilationUnit parseTokens(String fileName, CommonTokenStream tokens){
        JocParser jocParser = new JocParser(tokens);
        ParseTree tree = jocParser.file();

        return new CompleteVisitor(fileName).visit(tree);
    }

    private static CommonTokenStream getTokens(CharStream stream) {
        JocLexer lexer = new JocLexer(stream);
        return new CommonTokenStream(lexer);
    }
}
