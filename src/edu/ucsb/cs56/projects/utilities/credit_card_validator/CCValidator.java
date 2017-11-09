package edu.ucsb.cs56.projects.utilities.credit_card_validator;
import java.util.Scanner;
/**
* @author Jonathan Easterman and Ishi Von Meier
* @version  Winter 2015, CS 56
*/
public class CCValidator {
    
	/**
	* @param cardNumber String representation of a credit card number
	* @return boolean value that tells whether a credit card number is valid
	*/
    public static boolean isValid(String cardNumber) {
		cardNumber = cardNumber.replace(" ", "");
		cardNumber = cardNumber.replace("-","");
		if(cardNumber.length() != 16) { return false; }
    	String reversedNumber = new StringBuilder(cardNumber).reverse().toString();
    	char [] reversedDigitArray = reversedNumber.toCharArray();
	/*
	   temporarily holds a single digit from the character array
	*/
        char tempDigitHolder;
	/*
	   holds the single digit of the credit card number as an integer
	*/
        int singleDigit;
	/*
	   the sum after adding up all values in the algorithm
	*/
        int sum = 0;
	
	// Luhn algorithm for checking a valid credit card

    // First find the sum according to the algorithm
        if (reversedNumber.length() == 15) {
        	for (int x = 0; x < 15; x++) {
        		if (x%2 == 1) {
					tempDigitHolder = (char)(int) reversedDigitArray[x];
					singleDigit = Character.getNumericValue(tempDigitHolder);
					singleDigit = singleDigit * 2; // double every other digit starting from first digit

					if (singleDigit > 9) {
				    	singleDigit = singleDigit%10 + 1; // 10 = 1 + 0 so take %10 and add 1
				    	sum += singleDigit;
					}
					else {
				    	sum += singleDigit;
					}
				}

			    else {
					tempDigitHolder = (char)(int) reversedDigitArray[x];
					singleDigit = Character.getNumericValue(tempDigitHolder);
		        	sum += singleDigit;
		        }
        	}
        }
		else {
			for (int x = 0; x < 16; x++){ // Starting from the 1st digit, we double every other number
									  // Then sum each of the digits in the modified digit list
				if (x%2 == 1) {
					tempDigitHolder = (char)(int) reversedDigitArray[x];
					singleDigit = Character.getNumericValue(tempDigitHolder);
					singleDigit = singleDigit * 2; // double every other digit starting from first digit

					if (singleDigit > 9) {
				    	singleDigit = singleDigit%10 + 1; // 10 = 1 + 0 so take %10 and add 1
				    	sum += singleDigit;
					}
					else {
				    	sum += singleDigit;
					}
				}

			    else {
					tempDigitHolder = (char)(int) reversedDigitArray[x];
					singleDigit = Character.getNumericValue(tempDigitHolder);
		        	sum += singleDigit; // if we are looking at o
			    }

			}
		}

	// if sum is a multiple of 10 and is not equal to 0, then cc number is valid
		if(sum%10 == 0 && sum != 0) {
		    return true;
		}
		// else not a valid number
		else {
		    return false;
		}
	
	}


	/**
	* Check for card type based on first couple digits:
	* visas start in 4, mastercards are 51-55, etc
	* @param cardNumber String representation of credit card number
	* @return string valuae that tells what bank the card belongs to
	*/
	public static String getCardType(String cardNumber){

		//String cardNumber = new StringBuilder(cardNumber).toString();
    	char [] digitArray = cardNumber.toCharArray();		

		int firstNumber = Character.getNumericValue(digitArray[0]);
		int secondNumber = Character.getNumericValue(digitArray[1]);
		String strFirstSix = "";

		//get first 6 digits (necessary to check for discover card)
		for(int i = 0; i<6; i++)
		{
			strFirstSix += digitArray[i];
		}
		int firstSix = Integer.parseInt(strFirstSix);

		//visa
		if (firstNumber==4){return "Visa";}
		//mastercard
		else if (firstNumber==5 && secondNumber>=1 && secondNumber<=5){return "Mastercard";}
		//amex
		else if (firstNumber==3 && (secondNumber==4 || secondNumber==7)){return "Amex";}
		//discover
		else if ((firstSix >= 601100 && firstSix<601200) || (firstSix>= 622126 && firstSix <= 622925) 
			|| (firstSix >= 644000 && firstSix< 660000)){return "Discover";}
	
		return "";
	}

    
}
