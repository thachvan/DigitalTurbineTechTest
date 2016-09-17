package com.digitalturbine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;

public class RandomPairs {
	List<Integer> primes = new ArrayList<Integer>();
	List<Integer> perfectSquares = new ArrayList<Integer>();
	List<Integer> nonPrimesNonPerfectSquares = new ArrayList<Integer>();
	List<Character> vowels = new ArrayList<Character>();
	List<Character> consonants = new ArrayList<Character>();
	List<String> pairs = new ArrayList<String>();
	String analyzedResult = new String();

	public RandomPairs() {
		generateNumberLists(99);
		generateCharacterList();
	}

	/*
	 * Generate pairs with X, Y, Z
	 */
	@SuppressWarnings("unchecked")
	public void generatePairs(int X, int Y, int Z) {
		int number;
		char character;
		int letterWins = 0;
		int numberWins = 0;
		int letterStreak = 0;
		int numberStreak = 0;
		int maxLetterStreak = 0;
		int maxNumberStreak = 0;

		for (int i = 0; i < X; i++) {
			number = getNumberOfPair(Y);
			character = getLetterOfPair(Z);
			pairs.add(Integer.toString(number) + character);

			// analyze
			if ((int) character - 96 < number) {
				letterWins++;
				letterStreak++;

				if (maxLetterStreak < letterStreak) {
					maxLetterStreak = letterStreak;
				}

				numberStreak = 0;
			} else {
				numberWins++;
				numberStreak++;

				if (maxNumberStreak < numberStreak) {
					maxNumberStreak = numberStreak;
				}

				letterStreak = 0;
			}
		}

		// create JSON for output
		
		JSONObject letters = new JSONObject();
		letters.put("wins", letterWins);
		letters.put("streak", maxLetterStreak);

		JSONObject numbers = new JSONObject();
		numbers.put("wins", numberWins);
		numbers.put("streak", maxNumberStreak);

		JSONObject result = new JSONObject();
		result.put("letters", letters);
		result.put("numbers", numbers);

		analyzedResult = result.toJSONString();
	}

	/*
	 * Check to have a probability based on percentage
	 */
	private boolean getPossibility(double percentage) {
		// 100% chance
		if (percentage >= 100)
			return true;
		// 0% chance
		else if (percentage <= 0)
			return false;
		// specified chance
		else {
			Random random = new Random();
			// generate a number, then convert it into percentage and compare
			// chance
			if (random.nextDouble() * 100 <= percentage)
				return true;
			else
				return false;
		}
	}

	/*
	 * Generate a list of all Prime numbers in the range from 1 to max and a
	 * list of all other numbers
	 */
	private void generateNumberLists(int max) {
		// add 4 as the first perfect square
		perfectSquares.add(4);

		// add 1 as a prime number
		if (max < 1) {
			return;
		}
		primes.add(1);

		// add 2 as a prime number
		if (max < 2) {
			return;
		}
		primes.add(2);

		for (int i = 3; i <= max; i++) {
			int j = 1;
			boolean stop = false;

			// perfect squares
			if (i * i <= max) {
				perfectSquares.add(i * i);
			}

			// loop through the prime list
			while ((j < i) && (j < primes.size()) && (!stop)) {
				// Get modulus of the number and prime numbers existing in the
				// list (excluded 1)
				if (i % primes.get(j) == 0) { // not a prime
					if (!perfectSquares.contains(i)) {
						nonPrimesNonPerfectSquares.add(i);
					}
					stop = true;
				}
				j++;
			}

			if (!stop) { // a prime
				primes.add(i); // add to the list
			}
		}
	}

	/*
	 * Generate a list of vowels and a list of consonants
	 */
	private void generateCharacterList() {
		for (int i = 97; i <= 122; i++) {
			Character c = (char) i;
			if ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u')) { // vowels
				vowels.add(c);
			} else if (c != 'y') { // y is a consonant but not included in the
									// list
				consonants.add((char) i);
			}
		}
	}

	/*
	 * Generate a number for a pair
	 */
	private int getNumberOfPair(int Y) {
		// Requirement 2: Prime numbers should be Y times more likely than other
		// numbers

		int number;

		// convert Y to percentage
		double yPercent = Y * 100 / (Y + 1);

		if (getPossibility(yPercent)) {
			// pick a prime number
			number = (int) getRandomElement(primes);
		} else {
			// Requirement 3: Perfects squares should be one third (1/3)x as
			// likely as prime numbers
			if (getPossibility(yPercent / 3)) {
				// pick a perfect square
				number = (int) getRandomElement(perfectSquares);
			} else {
				// pick a non-prime and non-perfect-square number
				number = (int) getRandomElement(nonPrimesNonPerfectSquares);
			}
		}

		return number;
	}

	private char getLetterOfPair(int Z) {
		char c;

		// Requirement 4: vowels (a, e, i, o, u) should be Z times more likely
		// than consonants
		double zPercent = Z * 100 / (3 * Z + 1); // possibility to have a vowel
		// Requirement 5: The letter "y" should be twice (2x) as likely as
		// vowels
		double yPercent = 2 * Z * 100 / (3 * Z + 1); // possibility to have an
														// 'y'

		if (getPossibility(yPercent)) {
			c = 'y';
		} else if (getPossibility(zPercent)) {
			c = (char) getRandomElement(vowels);
		} else {
			c = (char) getRandomElement(consonants);
		}

		return c;
	}

	/*
	 * Get an element randomly in a list
	 */
	private Object getRandomElement(@SuppressWarnings("rawtypes") List objects) {
		Random random = new Random();

		return objects.get(random.nextInt(objects.size()));
	}

	public String getJSON() {
		return analyzedResult;
	}

	public void printPrimeList() {
		System.out.println("Prime numbers: " + Arrays.toString(primes.toArray()));
	}

	public void printNonPrimeList() {
		System.out.println(
				"Non-prime & non-perfect-square numbers: " + Arrays.toString(nonPrimesNonPerfectSquares.toArray()));
	}

	public void printPerfectSquaresList() {
		System.out.println("Perfect squares: " + Arrays.toString(perfectSquares.toArray()));
	}

	public void printVowelList() {
		System.out.println("Vowels: " + Arrays.toString(vowels.toArray()));
	}

	public void printConsonantList() {
		System.out.println("Consonants: " + Arrays.toString(consonants.toArray()));
	}

	public void printPairList() {
		System.out.println("Pairs: " + Arrays.toString(pairs.toArray()));
	}
}
