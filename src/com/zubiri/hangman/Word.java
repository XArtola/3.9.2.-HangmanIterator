/**
 * 
 */
package com.zubiri.hangman;

import java.util.ArrayList;

/**
 * @author Xabier
 *
 */
public class Word {

	private String word = "";

	public Word(String word) {

		this.word = word;

	}

	public String getWord() {

		return word;

	}

	public void setWord(String word) {

		if (!word.matches("^[a-z]+")) {

			this.word = word;

		}

	}
	
	/**@author ik013043z1
	 * 
	 * @param String: Contains the letter that is going to be checked in the word
	 * @return Boolean:  true if there is a match, false if not
	 */

	public boolean letterMatch(String letter) {

		boolean letterMatch = false;

		for (int i = 0; i < word.length(); i++) {

			if (word.substring(i, i + 1).matches(letter.substring(0, 1)))
				;
			letterMatch = true;

		}

		return letterMatch;

	}
	
	/**@author ik013043z1
	 * 
	 * @param String: Contains the letter that is going to be checked in the word
	 * @return ArrayList<Integer>: The positions of the letter in the word
	 */

	public ArrayList<Integer> matchPositions(String letter) {

		ArrayList<Integer> positions = new ArrayList<Integer>();

		for (int i = 0; i < word.length(); i++) {

			if (word.substring(i, i + 1).matches(letter.substring(0, 1)))

				positions.add(i);
		}

		return positions;

	}

}
