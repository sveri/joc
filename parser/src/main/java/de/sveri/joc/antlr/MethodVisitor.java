package de.sveri.joc.antlr;

import de.sveri.joc.antlr.generated.JocBaseVisitor;
import de.sveri.joc.antlr.generated.JocParser;

public class MethodVisitor extends JocBaseVisitor<String> {


    @Override
    public String visitMethodName(JocParser.MethodNameContext ctx) {
        return ctx.getText();
    }

    @Override
    protected String aggregateResult(String aggregate,
                                     String nextResult) {
        if (aggregate == null) {
            return nextResult;
        }

        if (nextResult == null) {
            return aggregate;
        }
        return super.aggregateResult(aggregate, nextResult);
    }
}
