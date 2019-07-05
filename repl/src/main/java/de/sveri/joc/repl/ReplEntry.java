package de.sveri.joc.repl;

import de.sveri.joc.interpreter.Interpreter;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;

public class ReplEntry {

    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal).build();

        Character mask = (args.length == 0)
                ? (char) 0
                : args[0].charAt(0);

        String line;
        do {
            line = reader.readLine("Enter password> ", mask);
            System.out.println("Got password: " + line);
        }
        while (line != null && line.length() > 0);
//        Executors.newSingleThreadExecutor().submit((Runnable) () -> {
//            while (true) {
//                System.out.print(">>>");
//                String line = System.console().readLine();
//                String output = Interpreter.fromRepl(line);
////            Object output;
////            output = JavaExec.eval(line);
//                System.out.println(output);
//            }

//        });
    }
}
