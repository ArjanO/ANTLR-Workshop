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
package nl.han.ica.ap.antlr.workshop.nlp.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for {@link Association}
 */
public class AssociationTest {
	@Test
	public void constructorTest() {
		Class child = new Class("Test");
		
		Association association = new Association(child);
		Multiplicity parentMultiplicity = association.getParentMultiplicity();
		Multiplicity childMultiplicity = association.getChildMultiplicity();
		
		assertEquals(child, association.getChildClass());
		assertEquals("0", parentMultiplicity.getLowerBound().getValue());
		assertEquals("1", parentMultiplicity.getUpperBound().getValue());
		assertEquals("0", childMultiplicity.getLowerBound().getValue());
		assertEquals("*", childMultiplicity.getUpperBound().getValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorNullTest() {
		new Association(null);
	}
}
