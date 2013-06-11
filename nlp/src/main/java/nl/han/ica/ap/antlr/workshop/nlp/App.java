/**
 * Copyright (c) 2013 HAN University of Applied Sciences
 * Arjan Oortgiese
 * Boyd Hofman
 * Joëll Portier
 * Michiel Westerbeek
 * Tim Waalewijn
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package nl.han.ica.ap.antlr.workshop.nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import nl.han.ica.ap.antlr.workshop.nlp.controller.ClassController;
import nl.han.ica.ap.antlr.workshop.nlp.export.ScruffyExport;
import nl.han.ica.ap.antlr.workshop.nlp.listener.ZelfstandignaamwoordListener;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Parse input form the console or from a file.
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
			NlpLexer lexer = new NlpLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			NlpParser parser = new NlpParser(tokens);
			
			ParseTree tree = parser.tekst();
			
			ClassController classController = new ClassController();
			
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(
					new ZelfstandignaamwoordListener(classController), 
					tree);
			
			ScruffyExport exporter = new ScruffyExport();
			
			String filepath = "target/export-" + 
					(System.currentTimeMillis()) + ".png";
			
			exporter.export(classController.getClasses(), filepath);
			
			openImage(filepath);
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
	
	private static void openImage(String path) {
		try {
			Process p = Runtime.getRuntime().exec(
					String.format("eog %s", path));
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}