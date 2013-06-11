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

/**
 * Multiplicity of associations (e.g. 0..*)
 */
public class Multiplicity {
	private Bound lowerBound;
	private Bound upperBound;
	
	/**
	 * Multiplicity of associations (e.g. 0..*)
	 * 
	 * @param lowerBound lower bound value.
	 * @param upperBound upper bound value.
	 * @throws IllegalArgumentException if lowerBound or upperBound is null.
	 */
	public Multiplicity(String lowerBound, String upperBound) {
		if (lowerBound == null || upperBound == null) {
			throw new IllegalArgumentException();
		}
		
		this.lowerBound = new Bound(lowerBound);
		this.upperBound = new Bound(upperBound);
	}
	
	/**
	 * Get lower bound.
	 * 
	 * @return Lower bound.
	 */
	public Bound getLowerBound() {
		return lowerBound;
	}
	
	/**
	 * Get upper bound.
	 * 
	 * @return Upper bound.
	 */
	public Bound getUpperBound() {
		return upperBound;
	}
	
	/**
	 * Set lower bound value.
	 * 
	 * @param boundValue Lower bound value.
	 * @throws IllegalArgumentException if boundValue is null.
	 */
	public void setLowerBound(String boundValue) {
		if (boundValue == null) {
			throw new IllegalArgumentException();
		}
		
		lowerBound.setValue(boundValue);
	}
	
	/**
	 * Set upper bound value.
	 * 
	 * @param boundValue Upper bound value.
	 * @throws IllegalArgumentException if boundValue is null.
	 */
	public void setUpperBound(String boundValue) {
		if (boundValue == null) {
			throw new IllegalArgumentException();
		}
		
		upperBound.setValue(boundValue);
	}
}
