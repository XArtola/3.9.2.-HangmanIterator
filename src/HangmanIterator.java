

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.ListIterator;

import com.zubiri.hangman.Word;

/**
 * @author ik013043z1
 *
 */
public class HangmanIterator {

	public static boolean validInput(String letter) {

		boolean isValid = true;

		if (letter.length() != 1)
			isValid = false;
		else if (!letter.matches("[a-z]"))
			isValid = false;

		return isValid;

	}

	public static boolean noGaps(ArrayList<String> guessed) {

		boolean noGaps = true;

		ListIterator<String> it = guessed.listIterator();

		while (it.hasNext()) {

			if (it.next().matches("_"))

				noGaps = false;
		}

		return noGaps;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Print the games' description
		System.out.println("Welcome to the famous HANGMAN game.");
		System.out.println("You will have to guess which surname of the people in class is hidden between the voids.");
		System.out.println(
				"For this, you will be able to enter 9 letters that may appear in the surname, or not. After this, you will only have a chance to guess the surname and win the game.");
		boolean playAgain = true;
		while (playAgain == true) {
			// Take randomly a surname between them

			ArrayList<Word> words = new ArrayList<Word>();
			Word word = new Word("");
			
			System.out.println("Enter the path of the file where the word are:");
			
			File wordsFile = new File(sc.nextLine());
			Scanner wordScanner = new Scanner(wordsFile);
			
			Scanner sc = new Scanner(toCopy);
			
			while (wordScanner.hasNextLine()) {

				String line = wordScanner.nextLine();

				String[] separatedWords = line.split(" ");

				for (int i = 0; i < separatedWords.length; i++) {

					word.setWord(separatedWords[i]);
					words.add(word);

				}

			}
			
		wordScanner.close();
			
			
			/*Word word1 = new Word("lazkano");
			words.add(word1);

			Word word2 = new Word("intxausti");
			words.add(word2);

			Word word3 = new Word("artola");
			words.add(word3);

			Word word4 = new Word("alberdi");
			words.add(word4);

			Word word5 = new Word("lekubide");
			words.add(word5);

			Word word6 = new Word("ortiz");
			words.add(word6);

			Word word7 = new Word("gonzalez");
			words.add(word7);*/
		

			Word secretWord = words.get(new Random().nextInt(words.size()));
			// Ask the first letter to the player
			System.out.println("Good luck, start with the first letter.");
			// Create a char array with the length of the maximum quantity of letters the
			// player can guess
			ArrayList<String> guessed = new ArrayList<String>();
			ArrayList<String> used = new ArrayList<String>();

			for (int i = 0; i < secretWord.getWord().length(); i++) {

				guessed.add("_");

			}

			int cnt = 0;
			ArrayList<Integer> positions = new ArrayList<Integer>();
			while (cnt < 9 && !noGaps(guessed)) {

				// Print the word with voids
				ListIterator<String> it = guessed.listIterator();
				while (it.hasNext()) {

					System.out.println(it.next());

				}
				System.out.println(" | Used letters" + used.toString());
				// Open a loop that will go asking to the player a possible letter in each turn

				// Create a string object with the letter's value
				String possibleLetter = sc.nextLine().toLowerCase();
				possibleLetter = possibleLetter.trim();

				if (validInput(possibleLetter)) {

					if (!used.contains(possibleLetter)) {

						if (secretWord.letterMatch(possibleLetter)) {

							positions = (ArrayList<Integer>) secretWord.matchPositions(possibleLetter);
							
							ListIterator<Integer> iteratorPositions = positions.listIterator();
							
							while(iteratorPositions.hasNext()) {
								
								guessed.set(positions.get(iteratorPositions.next()), possibleLetter.substring(0, 1));

								
							}

							used.add(possibleLetter);

							cnt++;

						}

						else {

							used.add(possibleLetter);
							cnt++;

						}

					} else {

						cnt++;

						System.out.println("You have used this letter before. You have missed a chance.");

					}
				}
				// If the player entered more than one characters, ask him/her to enter just one
				else {
					System.out.println("Don't cheat, please enter just a letter.");
				}
			}

			if (!noGaps(guessed)) {
				// Ask to the player a possible word
				System.out.println("It's the time, you have to guess the word.");

				String lastChance = sc.next();
				sc.nextLine();

				if (lastChance.matches(secretWord.getWord()))

					System.out.println("Congratulations, you guessed the word!!!");
				else {

					System.out.println("This is not the word");
				}

			}

			else {

				System.out.println("Congratulations, you guessed the word!!!");

			}

			// Ask if the player wants to play again
			System.out.println("Would you like to play again?(y/n)");
			boolean askAgain = true;
			while (askAgain == true) {
				String back = sc.next().toLowerCase();
				sc.nextLine();
				switch (back) {
				case "y":
					// Get out of the loop and play again
					askAgain = false;
					break;
				case "n":
					// Get out of the loop and terminate the program
					playAgain = false;
					askAgain = false;
					break;
				default:
					// Ask for a possible value again
					System.out.println("Please, select a possible value(y/n)");
					break;
				}

				sc.close();
			}
		}
	}
}
