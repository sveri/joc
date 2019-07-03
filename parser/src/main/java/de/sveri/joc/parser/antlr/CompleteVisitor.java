package de.sveri.joc.parser.antlr;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import de.sveri.joc.antlr.generated.JocBaseVisitor;
import de.sveri.joc.antlr.generated.JocParser;

public class CompleteVisitor extends JocBaseVisitor<CompilationUnit> {

    private final String fileName;
    private CompilationUnit compilationUnit = new CompilationUnit();

    public CompleteVisitor(String fileName) {
        this.fileName = Utils.capitalize(fileName);
    }

    @Override
    public CompilationUnit visitPackageDeclaration(JocParser.PackageDeclarationContext ctx) {

        return compilationUnit
                .setPackageDeclaration(ctx.packageName().getText());
    }

    @Override
    public CompilationUnit visitMethod(JocParser.MethodContext ctx) {
        String methodName = ctx.methodName().getText();
        String className = Utils.getClassName(fileName, methodName);

        ClassOrInterfaceDeclaration classDeclaration = compilationUnit.addClass(className);
        MethodDeclaration methodDeclaration = classDeclaration.addMethod(methodName);

        methodDeclaration.setType("void");

        PrintStatementVisitor printStatementVisitor = new PrintStatementVisitor();
        JocParser.PrintStatementContext printStatementContext = ctx.block().blockStatements().printStatement();
        BlockStmt blockStmt = printStatementContext.accept(printStatementVisitor);
        methodDeclaration.setBody(blockStmt);
        return compilationUnit;
    }
}
