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
 * Unit test for {@link Class}
 */
public class ClassTest {
	@Test
	public void constructorTest() {
		Class item = new Class("Test");
		
		assertEquals("Test", item.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorNullTest() {
		new Class(null);
	}
	
	@Test
	public void equalsTest() {
		Class class1 = new Class("Work");
		Class class2 = new Class("Work");
		Class class3 = new Class("School");
		
		assertTrue(class1.equals(class2));
		assertFalse(class1.equals(class3));
	}
	
	@Test
	public void addAssociationTest() {
		Class work = new Class("Work");
		Class school = new Class("School");
		
		work.addAssociation(school);
		
		assertEquals(1, work.getAssociations().size());
		assertEquals(school, work.getAssociations().get(0).getChildClass());
	}
}
