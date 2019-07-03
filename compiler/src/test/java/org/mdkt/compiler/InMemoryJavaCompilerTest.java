package org.mdkt.compiler;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ThrowableNotThrown")
class InMemoryJavaCompilerTest {

    @Test
    void compile_WhenTypical() throws Exception {
        String sourceCode = "package org.mdkt;\n" +
                "public class HelloClass {\n" +
                "   public String hello() { return \"hello\"; }" +
                "}";
        Class<?> helloClass = InMemoryJavaCompiler.newInstance().compile("org.mdkt.HelloClass", sourceCode);
        assertNotNull(helloClass);
        assertEquals(1, helloClass.getDeclaredMethods().length);
    }

    @Test
    void compileAll_WhenTypical() throws Exception {
        String cls1 = "public class A{ public B b() { return new B(); }}";
        String cls2 = "public class B{ public String toString() { return \"B!\"; }}";

        Map<String, Class<?>> compiled =
                InMemoryJavaCompiler.newInstance().addSource("A", cls1).addSource("B", cls2).compileAll();

        assertNotNull(compiled.get("A"));
        assertNotNull(compiled.get("B"));

        Class<?> aClass = compiled.get("A");
        Object a = aClass.getDeclaredConstructor().newInstance();
        assertEquals("B!", aClass.getMethod("b").invoke(a).toString());
    }

    @Test
    void compile_WhenSourceContainsInnerClasses() throws Exception {
        String sourceCode = "package org.mdkt;\n" +
                "public class HelloClass {\n" +
                "   private static class InnerHelloWorld { int inner; }\n" +
                "   public String hello() { return \"hello\"; }" +
                "}";
        Class<?> helloClass = InMemoryJavaCompiler.newInstance().compile("org.mdkt.HelloClass", sourceCode);
        assertNotNull(helloClass);
        assertEquals(1, helloClass.getDeclaredMethods().length);
    }

    @Test
    void compile_whenError() {
        String sourceCode = "package org.mdkt;\n" +
                "public classHelloClass {\n" +
                "   public String hello() { return \"hello\"; }" +
                "}";

        CompilationException exception = assertCompileExpectException(sourceCode);
        assertTrue(exception.getMessage().contains("Unable to compile the source"));
    }

    @Test
    void compile_WhenFailOnWarnings() {
        String sourceCode = "package org.mdkt;\n" +
                "public class HelloClass {\n" +
                "   public java.util.List<String> hello() { return new java.util.ArrayList(); }" +
                "}";
        assertCompileExpectException(sourceCode);
    }

    @Test
    void compile_WhenIgnoreWarnings() throws Exception {
        String sourceCode = "package org.mdkt;\n" +
                "public class HelloClass {\n" +
                "   public java.util.List<String> hello() { return new java.util.ArrayList(); }" +
                "}";
        Class<?> helloClass = InMemoryJavaCompiler.newInstance().ignoreWarnings().compile("org.mdkt.HelloClass",
                sourceCode);
        List<?> res = (List<?>) helloClass.getMethod("hello").invoke(helloClass.getDeclaredConstructor().newInstance());
        assertEquals(0, res.size());
    }

    @Test
    void compile_WhenWarningsAndErrors() {
        String sourceCode = "package org.mdkt;\n" +
                "public class HelloClass {\n" +
                "   public java.util.List<String> hello() { return new java.util.ArrayList(); }" +
                "}";
        assertCompileExpectException(sourceCode);
    }

    private CompilationException assertCompileExpectException(String sourceCode) {
        return assertThrows(CompilationException.class,
                () -> InMemoryJavaCompiler.newInstance().compile("org.mdkt.HelloClass", sourceCode)
        );
    }
}
