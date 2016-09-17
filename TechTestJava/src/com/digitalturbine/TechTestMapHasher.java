package com.digitalturbine;

import java.util.Arrays;
import java.util.HashMap;

public class TechTestMapHasher {

	public static void main(String[] args) {
		HashMap hashMap = new HashMap<String, Integer>();
		hashMap.put("www", 42);
		hashMap.put("th", 5);
		hashMap.put("fefre", 1443);
		hashMap.put("a", 7);
		hashMap.put("hyt", 825);
		hashMap.put("bz", 1);
		hashMap.put("oygy", 132);

		MapHasher mapHasher = new MapHasher(hashMap);

		System.out.print("Initialized: ");
		mapHasher.print();

		System.out.print("After scramble(): ");
		System.out.println(Arrays.toString(mapHasher.scramble().entrySet().toArray()));

		System.out.print("Sorted by value: ");
		mapHasher.sortByValue();
		mapHasher.print();

		System.out.print("Sorted by key: ");
		mapHasher.sortByKey();
		mapHasher.print();
	}

}
