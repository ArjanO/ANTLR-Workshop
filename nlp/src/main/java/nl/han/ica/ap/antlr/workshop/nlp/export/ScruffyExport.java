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
package nl.han.ica.ap.antlr.workshop.nlp.export;

import java.io.IOException;
import java.util.List;

import nl.han.ica.ap.antlr.workshop.nlp.model.Association;
import nl.han.ica.ap.antlr.workshop.nlp.model.Class;
import nl.han.ica.ap.antlr.workshop.nlp.model.Multiplicity;

/**
 * Exports classes to Scruffy diagram.
 */
public class ScruffyExport {
	/**
	 * Export to Scruffy format (like yuml).
	 * 
	 * @param classes Classes
	 * @return Scruffy syntax.
	 */
	public String exportSource(List<Class> classes) {
		StringBuffer sbText = new StringBuffer(255);
		
		for (Class item : classes) {			
			if (item.getAssociations().size() > 0) {
				if (sbText.length() != 0) {
					sbText.append(",");
				}
				
				for (Association asso : item.getAssociations()) {
					addAssociation(sbText, item, asso);
				}
			}
		}
		
		return sbText.toString();
	}
	
	/**
	 * Export to Scruffy.
	 * 
	 * @param classes Classes to export
	 * @param outpath File path.
	 */
	public void export(List<Class> classes, String outpath) {
		String source = exportSource(classes);
		
		try {
			Runtime.getRuntime().exec(new String[] {
				"suml", "--png", "--font-family=Purisa", "--scruffy", 
				String.format("--output=%s", outpath),
				String.format("\"%s\"", source)
			}).waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void addAssociation(
			StringBuffer sbText, Class item, Association asso) {
		sbText.append(String.format("[%s]", item.getName()));
		
		addMultiplicity(sbText, asso.getParentMultiplicity());
		sbText.append("-");
		addMultiplicity(sbText, asso.getChildMultiplicity());
		
		sbText.append(String.format(
				"[%s]", asso.getChildClass().getName()));
	}
	
	private void addMultiplicity(
			StringBuffer sbText, Multiplicity multiplicity) {
		sbText.append(multiplicity.getLowerBound().getValue());
		sbText.append("..");
		sbText.append(multiplicity.getUpperBound().getValue());
	}
}
