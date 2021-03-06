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
package nl.han.ica.ap.antlr.workshop.nlp.listener;

import nl.han.ica.ap.antlr.workshop.nlp.model.Class;
import nl.han.ica.ap.antlr.workshop.nlp.model.Association;
import nl.han.ica.ap.antlr.workshop.nlp.NlpBaseListener;
import nl.han.ica.ap.antlr.workshop.nlp.NlpParser.BijwoordContext;
import nl.han.ica.ap.antlr.workshop.nlp.NlpParser.TelwoordContext;
import nl.han.ica.ap.antlr.workshop.nlp.NlpParser.ZelfstandignaamwoordContext;
import nl.han.ica.ap.antlr.workshop.nlp.NlpParser.ZinContext;
import nl.han.ica.ap.antlr.workshop.nlp.controller.ClassController;

/**
 * Analyze parse tree.
 */
public class ZelfstandignaamwoordListener extends NlpBaseListener {
	private ClassController classController;
	
	private Class class1;
	private Class class2;
	
	private String bijwoord;
	private String telwoord;
	
	public ZelfstandignaamwoordListener(ClassController classController) {
		this.classController = classController;
	}
	
	@Override
	public void enterZin(ZinContext ctx) {
		class1 = null;
		class2 = null;
		
		bijwoord = null;
		telwoord = null;
	}
	
	@Override
	public void enterZelfstandignaamwoord(ZelfstandignaamwoordContext ctx) {
		if (class1 == null) {
			class1 = classController.getClassByName(ctx.getText());
		} else {
			class2 = classController.getClassByName(ctx.getText());
			
			Association a = class1.addAssociation(class2);
			
			if (bijwoord != null) {
				if (bijwoord.equals("maximaal")) {
					a.getChildMultiplicity().setUpperBound(telwoord);
				} else if (bijwoord.equals("minimaal")) {
					a.getChildMultiplicity().setLowerBound(telwoord);
				}
			}
		}
	}
	
	@Override
	public void enterBijwoord(BijwoordContext ctx) {
		bijwoord = ctx.getText();
	}
	
	@Override
	public void enterTelwoord(TelwoordContext ctx) {
		telwoord = ctx.getText();
	}
}
