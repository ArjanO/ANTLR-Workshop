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
package nl.han.ica.ap.antlr.workshop.nlp.util;

/**
 * Plural tools.
 */
public class Plural {
	private Plural() {
	}

	/**
	 * Make the input string singular.
	 * 
	 * @param input plural input string.
	 * @return singular string.
	 */
	public static String singular(String input) {
		return getInputSingular(input);
	}
	
	/**
	 * Check if the name equals as plural
	 * 
	 * @param orginional Origional name.
	 * @param name Name to compare.
	 * @return True if the plural is equal
	 */
	public static boolean pluralEquals(String orginional, String name) {
		if (name.equalsIgnoreCase(orginional + "s")) {
			return true;
		} else if (orginional.equalsIgnoreCase(name + "s")) {
			return true;
		} else if (name.equalsIgnoreCase(orginional + "'s")) {
			return true;
		} else if (orginional.equalsIgnoreCase(name + "'s")) {
			return true;
		} else if (name.equalsIgnoreCase(getClassSingular(orginional))) {
			return true;
		} else if (orginional.equalsIgnoreCase(getInputSingular(name))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get the word stem of the input noun.
	 */
	private static String getInputSingular(String name) {
		int inputLength = name.length();

		if (name.endsWith("en")) {
			if (name.charAt(inputLength - 3) == name.charAt(inputLength - 4)) {
				// Get stem of noun (- "nen" or "pen" etc).
				name = name.substring(0, inputLength - 3); 
			} else {
				// Get stem of noun (- "en").
				name = name.substring(0, inputLength - 2);
				inputLength = name.length() - 1;
				// Check if letter before the second to last is a vowel.
				if (name.charAt(inputLength - 2) == 'a'
						|| name.charAt(inputLength - 2) == 'e'
						|| name.charAt(inputLength - 2) == 'o'
						|| name.charAt(inputLength - 2) == 'i'
						|| name.charAt(inputLength - 2) == 'u'
						|| name.charAt(inputLength - 2) == 'y') {
					return name;
				} else {
					char charToAdd = name.charAt(inputLength - 1);
					name = name.substring(0, inputLength) + charToAdd
							+ name.substring(inputLength);
				}
			}
		}
		return name;
	}

	/**
	 * Get the word stem of the existing noun.
	 */
	private static String getClassSingular(String cInList) {
		int cInListLength = cInList.length();

		if (cInList.endsWith("en")) {
			if (cInList.charAt(cInListLength - 3) == cInList
					.charAt(cInListLength - 4)) {
				// Get stem of noun (- "nen" or "pen" etc).
				cInList = cInList.substring(0, cInListLength - 3); 
			} else {
				// Get stem of noun (- "en").
				cInList = cInList.substring(0, cInListLength - 2);
				cInListLength = cInList.length() - 1;
				// Check if letter before the second to last is a vowel.
				if (cInList.charAt(cInListLength - 2) == 'a'
						|| cInList.charAt(cInListLength - 2) == 'e'
						|| cInList.charAt(cInListLength - 2) == 'o'
						|| cInList.charAt(cInListLength - 2) == 'i'
						|| cInList.charAt(cInListLength - 2) == 'u'
						|| cInList.charAt(cInListLength - 2) == 'y') {
					return cInList;
				} else {
					char charToAdd = cInList.charAt(cInListLength - 1);
					cInList = cInList.substring(0, cInListLength) + charToAdd
							+ cInList.substring(cInListLength);
				}
			}
		}
		return cInList;
	}
}
