package org.mdkt.compiler;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by trung on 5/3/15.
 */
class SourceCode extends SimpleJavaFileObject {
	private final String contents;
	private final String className;

	SourceCode(String className, String contents) {
		super(URI.create("string:///" + className.replace('.', '/')
				+ Kind.SOURCE.extension), Kind.SOURCE);
		this.contents = contents;
		this.className = className;
	}

	String getClassName() {
		return className;
	}

	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return contents;
	}
}
