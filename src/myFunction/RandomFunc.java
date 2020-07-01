package myFunction;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class RandomFunc {
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
	private static final String alphaUpperCase = alpha.toUpperCase();
	private static final String digits = "0123456789";
	private static final String specials = "@#$";
	private static final String ALL = alpha + alphaUpperCase + digits + specials;
	
	private Random generator = new Random();
	
	public String randomAll(int numberOfCharactor) {
		List<String> result = new ArrayList<>();
		Consumer<String> appendChar = s ->{
			int number = randomNumber(0, s.length() - 1);
		};
		appendChar.accept(digits);
		appendChar.accept(specials);
		while (result.size() < numberOfCharactor) {
			appendChar.accept(ALL);
		}
		Collections.shuffle(result, generator);
		return String.join("", result);
	}
	
	public int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}
}
