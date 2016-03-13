package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import java.util.*; 


/** Class that generates a MasterCard credit card number
* @author Jonathan Easterman Ishi Von Meier
* @version  Winter 2015, CS 56
*/
public class MasterCard {
	public static String cardNumber;
        public static final int VALIDLENGTH = 16;
	/** Generates card number for this type of card
    * @return String representation of a MasterCard credit card number
    */
	public static String generateCard() {
		String temp = new String();
		String newCard = new String();
		int tmp;
		Random rand = new Random();

		int cardDigits[] = new int[16];

		// For Mastercard, 1st digit is always 5
		cardDigits[0] = 5;
		int randNumberAdded = rand.nextInt(5);    
		
		cardDigits[1] = 1 + randNumberAdded;  //gives us random second digit between 1 and 5		
		newCard += cardDigits[0];
		newCard += cardDigits[1];
		// Initialize the 3rd-15th digit to a random number
		for (int i = 2; i < 15; i++) {
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
