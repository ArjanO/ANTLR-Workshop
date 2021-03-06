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

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import nl.han.ica.ap.antlr.workshop.nlp.model.Association;
import nl.han.ica.ap.antlr.workshop.nlp.model.Class;
import nl.han.ica.ap.antlr.workshop.nlp.model.Multiplicity;
import nl.han.ica.ap.antlr.workshop.nlp.NlpLexer;
import nl.han.ica.ap.antlr.workshop.nlp.NlpParser;
import nl.han.ica.ap.antlr.workshop.nlp.controller.ClassController;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Unit test for {@link ZelfstandignaamwoordListener}
 */
@RunWith(Parameterized.class)
public class ZelfstandignaamwoordListenerMultiplicityTest {
	private ZelfstandignaamwoordListener listener;
	private ClassController classController;
	
	private Class class1;
	private Class class2;
	
	private Association class1Association;
	
	private String sentence;
	private String lowerBound;
	private String upperBound;
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] result = new Object[][] {
			{
				"De HAN heeft maximaal 100 studenten.",
				"HAN",
				"studenten",
				"0",
				"100"
			},
			{
				"De HAN heeft minimaal 100 studenten.",
				"HAN",
				"studenten",
				"100",
				"*"
			}
		};
		
		return Arrays.asList(result);
	}
	
	public ZelfstandignaamwoordListenerMultiplicityTest(
			String sentence, String class1Name, String class2Name, 
			String lowerBound, String upperBound) {
		this.sentence = sentence;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		
		classController = EasyMock.createMock(ClassController.class);
		
		listener = new ZelfstandignaamwoordListener(classController);
		
		class1 = EasyMock.createMock(Class.class);
		class2 = EasyMock.createMock(Class.class);
		
		class1Association = new Association(class2);
		
		EasyMock.expect(class1.addAssociation(class2))
			.andReturn(class1Association).atLeastOnce();
		
		EasyMock.expect(classController.getClassByName(class1Name))
			.andReturn(class1).atLeastOnce();
		EasyMock.expect(classController.getClassByName(class2Name))
			.andReturn(class2).atLeastOnce();
		
		EasyMock.replay(class1);
		EasyMock.replay(class2);
		EasyMock.replay(classController);
	}
	
	@After
	public void after() {
		EasyMock.verify(classController);
		EasyMock.verify(class1);
		EasyMock.verify(class2);
	}
	
	@Test
	public void test() {		
		walk(sentence);
		
		Multiplicity multiplicity = class1Association.getChildMultiplicity();
		
		assertEquals(lowerBound, multiplicity.getLowerBound().getValue());
		assertEquals(upperBound, multiplicity.getUpperBound().getValue());
	}
	
	/**
	 * Create parse tree of sentence and calls walks listener.
	 * 
	 * @param sentence Sentence.
	 */
	private void walk(String sentence) {
		ANTLRInputStream input = new ANTLRInputStream(sentence);
		
		NlpLexer lexer = new NlpLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		NlpParser parser = new NlpParser(tokens);
		
		ParseTree tree = parser.tekst();
		
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(listener, tree);
	}
}
