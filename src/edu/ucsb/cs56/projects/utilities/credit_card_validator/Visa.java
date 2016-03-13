package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import java.util.*; 


/** Class that generates a Visa credit card number
* @author Jonathan Easterman Ishi Von Meier
* @version  Winter 2015, CS 56
*/
public class Visa {
	public static String cardNumber;
        public static final int VALIDLENGTH  = 16; 
	public static String generateCard() {
		String temp = new String();
		String newCard = new String();
		int tmp;
		Random rand = new Random();

		int cardDigits[] = new int[16];

		// For Visa, 1st digit is always 4
		cardDigits[0] = 4;
		newCard += cardDigits[0];
		// Initialize the 2nd-15th digit to a random number
		for (int i = 1; i < 15; i++) {
			cardDigits[i] = rand.nextInt(10);
			newCard += cardDigits[i];
		}

		String luhnString = new String();

		for (int i = 14; i >= 0; i--) {
			
			if (i%2 == 0) {
				tmp = cardDigits[i] * 2;
			}
			else 
				tmp = cardDigits[i];

			luhnString += tmp;
		}

		int luhnSum = 0;

		for (int i = 0; i < luhnString.length(); i++) {
			temp = Character.toString(luhnString.charAt(i));
			luhnSum += Integer.parseInt(temp);
		}

		// Calculate the check digit based on the luhnSum
		int checkDigit = 10 - (luhnSum%10);
		if (checkDigit == 10)
			checkDigit = 0;

		cardDigits[15] = checkDigit;

		newCard += cardDigits[15];

		return newCard;
	}
}
