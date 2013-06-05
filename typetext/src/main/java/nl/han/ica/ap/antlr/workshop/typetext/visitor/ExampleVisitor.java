package nl.han.ica.ap.antlr.workshop.typetext.visitor;

import nl.han.ica.ap.antlr.workshop.TypeBaseVisitor;
import nl.han.ica.ap.antlr.workshop.TypeParser.StatementContext;
import nl.han.ica.ap.antlr.workshop.TypeParser.TextContext;

/**
 * Example implementation with visitor.
 * 
 * @author Arjan and Tim
 */
public class ExampleVisitor extends TypeBaseVisitor<Void> {
	@Override
	public Void visitStatement(StatementContext ctx) {
		if (ctx.type().getText().equals("Boek")) {
			return super.visitStatement(ctx);
		} else {
			return defaultResult();
		}
	}
	
	@Override
	public Void visitText(TextContext ctx) {
		System.out.println(ctx.getText());
		return super.visitText(ctx);
	}
}
