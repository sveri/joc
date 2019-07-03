package de.sveri.joc.antlr;

import com.github.javaparser.ast.CompilationUnit;
import de.sveri.joc.antlr.generated.JocBaseVisitor;
import de.sveri.joc.antlr.generated.JocParser;

public class PackageVisitor extends JocBaseVisitor<CompilationUnit> {

    @Override
    public CompilationUnit visitPackageDeclaration(JocParser.PackageDeclarationContext ctx) {

        CompilationUnit compilationUnit = new CompilationUnit();
        return compilationUnit
                .setPackageDeclaration(ctx.packageName().getText());
    }

    @Override
    public CompilationUnit visitPrintStatement(JocParser.PrintStatementContext ctx) {
        return super.visitPrintStatement(ctx);
    }

    @Override
    public CompilationUnit visitBlock(JocParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    protected CompilationUnit aggregateResult(CompilationUnit aggregate,
                                              CompilationUnit nextResult) {
        if (aggregate == null) {
            return nextResult;
        }

        if (nextResult == null) {
            return aggregate;
        }
        return super.aggregateResult(aggregate, nextResult);
    }
}
