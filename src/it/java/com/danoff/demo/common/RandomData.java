package com.danoff.demo.common;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomData {

	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static Long getRandomId() {
		Random rand = new Random();
		return rand.nextLong();
	}

	public static String getRandomElementOfList(List<String> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

}
