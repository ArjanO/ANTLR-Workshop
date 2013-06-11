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

import java.util.ArrayList;
import java.util.List;

/**
 * Stores candidate classes.
 */
public class Class {
	private String name;
	private List<Association> associations;
	
	/**
	 * Create class.
	 * 
	 * @param name Name of the class.
	 * @throws IllegalArgumentException if name is null.
	 */
	public Class(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		
		this.name = name;
		this.associations = new ArrayList<Association>();
	}
	
	/**
	 * Get name of the class.
	 * 
	 * @return Name of the class.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Add child association to parent class.
	 * 
	 * @param child Child class.
	 * @return Created association.
	 */
	public Association addAssociation(Class child) {
		for (Association asso : associations) {
			if (asso.getChildClass().equals(child)) {
				return asso; // Association already exists.
			}
		}
		
		Association result = new Association(child);
		
		associations.add(result);
		
		return result;
	}
	
	/**
	 * Get class associations.
	 * 
	 * @return Associations
	 */
	public List<Association> getAssociations() {
		return associations;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Class) {
			Class other = (Class)obj;
			
			return name.equals(other.name);
		} else {
			return super.equals(obj);
		}
	}
}
