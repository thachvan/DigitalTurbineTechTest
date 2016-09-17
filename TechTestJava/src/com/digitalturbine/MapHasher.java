package com.digitalturbine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

public class MapHasher {
	private HashMap<String, Integer> myHash;

	public MapHasher(HashMap<String, Integer> hashmap) {
		myHash = hashmap;
	}

	public MapHasher(ArrayList<String> keys, ArrayList<Integer> values) {
		int size = keys.size();

		myHash = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < size; i++) {
			myHash.put(keys.get(i), values.get(i));
		}
	}

	public HashMap<String, Integer> scramble() {
		HashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
		List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(myHash.entrySet());
		Entry<String, Integer> entry;
		Random random = new Random();
		int index;

		while (!entries.isEmpty()) {
			index = random.nextInt(entries.size());
			entry = entries.get(index);
			result.put(entry.getKey(), entry.getValue());
			entries.remove(index);
		}

		return result;
	}

	public int sum() {
		Collection<Integer> values = myHash.values();
		int sum = 0;

		for (int value : values) {
			sum += value;
		}

		return sum;
	}

	public void sortByValue() {
		List<Entry<String, Integer>> entries = new LinkedList<Entry<String, Integer>>(myHash.entrySet());

		Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> firstEntry, Entry<String, Integer> secondEntry) {
				return firstEntry.getValue().compareTo(secondEntry.getValue());
			}

		});

		myHash.clear();
		myHash = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : entries) {
			myHash.put(entry.getKey(), entry.getValue());
		}
	}

	public void sortByKey() {
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(new Comparator<String>() {
			@Override
			public int compare(String firstKey, String secondKey) {
				return (getStringSize(firstKey).compareTo(getStringSize(secondKey)));
			}
		});
		treeMap.putAll(myHash);

		myHash.clear();
		myHash = new LinkedHashMap<String, Integer>();
		myHash.putAll(treeMap);
	}

	private Integer getStringSize(String text) {
		byte[] bytes = text.getBytes();
		int sum = 0;

		for (byte b : bytes) {
			sum += (int) b;
		}

		return sum;
	}

	public void print() {
		System.out.println(Arrays.toString(myHash.entrySet().toArray()));
	}
}
