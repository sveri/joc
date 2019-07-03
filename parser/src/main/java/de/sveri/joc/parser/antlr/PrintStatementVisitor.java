package de.sveri.joc.parser.antlr;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import de.sveri.joc.antlr.generated.JocBaseVisitor;
import de.sveri.joc.antlr.generated.JocParser;

public class PrintStatementVisitor extends JocBaseVisitor<BlockStmt> {

    @Override
    public BlockStmt visitPrintStatement(JocParser.PrintStatementContext ctx) {
        BlockStmt blockStmt = new BlockStmt();
        ExpressionStmt expressionStmt = new ExpressionStmt();
        MethodCallExpr methodCallExpr = new MethodCallExpr("System.out.println");

        Expression argumentExpression = getArgumentExpression(ctx);

        NodeList<Expression> argumentExpressions = new NodeList<>();
        argumentExpressions.add(argumentExpression);
        methodCallExpr.setArguments(argumentExpressions);
        expressionStmt.setExpression(methodCallExpr);
        NodeList<Statement> statements = new NodeList<>();
        statements.add(expressionStmt);
        blockStmt.setStatements(statements);
        return blockStmt;
    }

    private Expression getArgumentExpression(JocParser.PrintStatementContext ctx) {
        Expression argumentExpression;
        if(ctx.literal().IntegerLiteral() != null){
            argumentExpression = new IntegerLiteralExpr(ctx.literal().getText());
        } else {
            argumentExpression = new StringLiteralExpr(removeQuotes(ctx.literal().getText()));
        }
        return argumentExpression;
    }

    private static String removeQuotes(String s){
        if(s.length() == 2) {
            return "";
        }
        return s.substring(1, s.length() - 1);
    }
}
