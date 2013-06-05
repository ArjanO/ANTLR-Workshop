package nl.han.ica.ap.antlr.workshop.typetext.listener;

import nl.han.ica.ap.antlr.workshop.TypeBaseListener;
import nl.han.ica.ap.antlr.workshop.TypeParser.TextContext;
import nl.han.ica.ap.antlr.workshop.TypeParser.TypeContext;

/**
 * Example implementation with listener.
 * 
 * @author Arjan and Tim
 */
public class ExampleListener extends TypeBaseListener {
	private boolean printText;
	
	@Override
	public void enterType(TypeContext ctx) {
		printText = ctx.getText().equals("Book");
	}
	
	@Override
	public void enterText(TextContext ctx) {
		if (printText) {
			System.out.println(ctx.getText());
		}
	}
}