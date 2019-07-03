package de.sveri.joc;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import de.sveri.joc.antlr.CompleteVisitor;
import de.sveri.joc.antlr.MethodVisitor;
import de.sveri.joc.antlr.PackageVisitor;
import de.sveri.joc.antlr.PrintStatementVisitor;
import de.sveri.joc.antlr.generated.JocLexer;
import de.sveri.joc.antlr.generated.JocParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    static CompilationUnit parseInput(String fileName, String input){
        CommonTokenStream tokens = getTokens(CharStreams.fromString(input));
        JocParser jocParser = new JocParser(tokens);
        ParseTree tree = jocParser.file();

        return new CompleteVisitor(fileName).visit(tree);
    }

    private static CommonTokenStream getTokens(CharStream stream) {
        JocLexer lexer = new JocLexer(stream);
        return new CommonTokenStream(lexer);
    }
}