package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import java.util.*; 


/** Class that generates a Discover credit card number
* @author Jonathan Easterman Ishi Von Meier
* @version  Winter 2015, CS 56
*/

public class Discover {
	public static String cardNumber;
        public static final int VALIDLENGTH  = 16;
	/** Generates card number for this type of card
    * @return String representation of a Discover credit card number
    */
	public static String generateCard() {
		String temp = new String();
		String newCard = new String();
		int tmp;
		int i;
		int randNumberAdded;
		Random rand = new Random();
		

		int cardDigits[] = new int[16];

		// For Discover, 1st digit is always 6
		//cardDigits[0] = 6;
		//newCard += cardDigits[0];
		
		int randomCase = 1 + rand.nextInt(3); //random case between 1 and 4
		
		switch (randomCase) {
			case 1:
				newCard += "6011";
				break;	
			case 2:
				randNumberAdded = rand.nextInt(800);
				newCard+= Integer.toString(622126 + randNumberAdded);
				break;
			case 3:
				randNumberAdded = rand.nextInt(6);
				newCard+= Integer.toString(644 + randNumberAdded);
				break;
			case 4: 
				newCard += "65";
				break;
		}
		
		
		for(i=0; i < newCard.length(); i++) {        //update cardDigits array
			cardDigits[i] = Character.getNumericValue(newCard.charAt(i));
		}
		
		
		// Initialize the next-15th digit to a random number
		for (i = newCard.length(); i < 15; i++) { //newCard.length() gives us next index
			cardDigits[i] = rand.nextInt(10);
			newCard += cardDigits[i];
		}
		

		String luhnString = new String();

		for (i = 14; i >= 0; i--) {
			
			if (i%2 == 0) {
				tmp = cardDigits[i] * 2;
			}
			else 
				tmp = cardDigits[i];

			luhnString += tmp;
		}

		int luhnSum = 0;

		for (i = 0; i < luhnString.length(); i++) {
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
