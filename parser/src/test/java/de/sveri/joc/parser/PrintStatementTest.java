package de.sveri.joc.parser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.Statement;
import de.sveri.joc.parser.antlr.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("OptionalGetWithoutIsPresent")
class PrintStatementTest {

    private String mainMethodName = "main";

    @Test
    void testPrintStatementWithOneCharacterString() {
        String input =
                "package foo.bar;" + "\n\n" +
                        mainMethodName + "(args){println(\"3\")}";

        MethodDeclaration methodDeclaration = parseAndGetMainMethod(input);
        assertEquals(mainMethodName, methodDeclaration.getName().getIdentifier());

        Statement statement = methodDeclaration.getBody().get().getStatements().get(0);
        assertEquals("System.out.println(\"3\");", statement.toString());
    }

    @Test
    void testPrintStatementWithLongerString() {
        String input =
                "package foo.bar;" + "\n\n" +
                        mainMethodName + "(args){println(\"fastbar\")}";

        MethodDeclaration methodDeclaration = parseAndGetMainMethod(input);
        assertEquals(mainMethodName, methodDeclaration.getName().getIdentifier());

        Statement statement = methodDeclaration.getBody().get().getStatements().get(0);
        assertEquals("System.out.println(\"fastbar\");", statement.toString());
    }

    @Test
    void testPrintStatementWithSmallInt() {
        String input =
                "package foo.bar;" + "\n\n" +
                        mainMethodName + "(args){println(3)}";

        MethodDeclaration methodDeclaration = parseAndGetMainMethod(input);
        assertEquals(mainMethodName, methodDeclaration.getName().getIdentifier());

        Statement statement = methodDeclaration.getBody().get().getStatements().get(0);
        assertEquals("System.out.println(3);", statement.toString());
    }

    @Test
    void testPrintStatementWithBigInt() {
        String input =
                "package foo.bar;" + "\n\n" +
                        mainMethodName + "(args){println(333333333)}";

        MethodDeclaration methodDeclaration = parseAndGetMainMethod(input);
        assertEquals(mainMethodName, methodDeclaration.getName().getIdentifier());

        Statement statement = methodDeclaration.getBody().get().getStatements().get(0);
        assertEquals("System.out.println(333333333);", statement.toString());
    }

    MethodDeclaration parseAndGetMainMethod(String input) {
        String fileName = "TestFile";
        CompilationUnit compilationUnit = Main.parseString(fileName, input);
        ClassOrInterfaceDeclaration clazz = compilationUnit.getClassByName(Utils.getClassName(fileName,
                mainMethodName)).get();
        List<MethodDeclaration> methods = clazz.getMethodsByName(mainMethodName);
        return methods.get(0);
    }
}
