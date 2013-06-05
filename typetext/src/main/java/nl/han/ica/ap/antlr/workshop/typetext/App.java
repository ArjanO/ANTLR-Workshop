package nl.han.ica.ap.antlr.workshop.typetext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import nl.han.ica.ap.antlr.workshop.TypeLexer;
import nl.han.ica.ap.antlr.workshop.TypeParser;
import nl.han.ica.ap.antlr.workshop.typetext.listener.ExampleListener;
import nl.han.ica.ap.antlr.workshop.typetext.visitor.ExampleVisitor;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Parse input form the console or from a file.
 * 
 * @author Arjan and Tim
 */
public class App {
	public static void main(String[] args) {
		ANTLRInputStream input = null;
		
		if (args.length > 0) {
			input = getStreamFromFile(args[0]);
		} else {			
			input = getStreamFromStdin();
		}
		
		if (input != null) {
			TypeLexer lexer = new TypeLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			TypeParser parser = new TypeParser(tokens);
			
			ParseTree tree = parser.file();
			
			ExampleVisitor visitor = new ExampleVisitor();
			visitor.visit(tree);
			
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(new ExampleListener(), tree);
		}
	}
	
	private static ANTLRInputStream getStreamFromStdin() {
		ANTLRInputStream input = null;
		
		try {
			input = new ANTLRInputStream(System.in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return input;
	}
	
	private static ANTLRInputStream getStreamFromFile(String path) {
		ANTLRInputStream input = null;
		InputStream is = null;
		
		if (path != null) {
			try {
				is = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		try {
			input = new ANTLRInputStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return input;
	}
}
