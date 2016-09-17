package com.digitalturbine;

import java.util.Random;

public class TechTestRandomPairs {
	public static void main(String[] args) {
		Random random = new Random();
		RandomPairs randomPairs = new RandomPairs();
		// X: from 1 million to less than 5 millions
		// Y: from 1 to 5
		// Z: from 1 to 5
		randomPairs.generatePairs((random.nextInt(1) + 3) * 1000000, random.nextInt(4) + 1, random.nextInt(4) + 1);
		System.out.println(randomPairs.getJSON());

		// randomPairs.printPrimeList();
		// randomPairs.printNonPrimeList();
		// randomPairs.printPerfectSquaresList();
		// randomPairs.printVowelList();
		// randomPairs.printConsonantList();
		
		// for testing with small data
		// randomPairs.generatePairs(100, random.nextInt(4) + 1, random.nextInt(4) + 1);
		// randomPairs.printPairList(); // will be too slow for big data
	}
}
