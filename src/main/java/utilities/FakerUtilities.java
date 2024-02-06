package utilities;

import java.util.Random;

public class FakerUtilities {
	public static int randomNumberCreation() {
		Random random = new Random();
		int randomNumber = random.nextInt(10000);
		return randomNumber;
	}
}
