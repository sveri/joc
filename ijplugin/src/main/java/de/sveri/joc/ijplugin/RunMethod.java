package de.sveri.joc.ijplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.vfs.VirtualFile;
import de.sveri.joc.interpreter.Interpreter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public class RunMethod extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            VirtualFile file = FileEditorManager.getInstance(e.getProject()).getSelectedEditor().getFile();
            InputStream is = file.getInputStream();
            System.out.println(Interpreter.fromIdea(file.getName().substring(0, file.getName().lastIndexOf(".")),
                    is));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
