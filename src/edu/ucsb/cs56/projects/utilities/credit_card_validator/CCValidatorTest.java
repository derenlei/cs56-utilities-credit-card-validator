package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** test class for CCValidator
* @author Jicheng Huang @ Christopher Langford
* @version Mantis Ticket for CS56, Spring 2011
* @see CCValidator
*/



public class CCValidatorTest {
    
    /*
       test isValid Method in CCValidator class
     */
    @Test public void testisValid() {
	Boolean result;

	String testCard2 = "5000000000000000";
	result = CCValidator.isValid(testCard2);
	assertEquals(false, result);

	String testCard3 = "4200000000000000";
	result = CCValidator.isValid(testCard3);
	assertEquals(true, result);

	String testCard4 = "6011768593893031";
	result = CCValidator.isValid(testCard4);
	assertEquals(true,result);
    }

    /*
       test isValid Method with card number contain spaces and "-"
     */
    @Test public void testCardWithSpace(){
	Boolean result = null;

	String testCard4 = "5000 0000 0000 0000";
	result = CCValidator.isValid(testCard4);
	assertEquals(false, result);

	String testCard5 = "4200 0000 0000 0000";
	result = CCValidator.isValid(testCard5);	
	assertEquals(true, result);

	String testCard6 = "5000-0000-0000-0000";
	result = CCValidator.isValid(testCard6);
	assertEquals(false, result);

	String testCard7 = "4200-0000-0000-0000";
	result = CCValidator.isValid(testCard7);
	assertEquals(true, result);
    }
    /*
      test getCardType method to return proper card type when given string of digits
    */
    @Test public void testCardType(){

	String card1 = "4716726695773496";
	String cardType = CCValidator.getCardType(card1);
	assertEquals("Visa",cardType);

	String card2 = "5231547739098168";
	cardType = CCValidator.getCardType(card2);
	assertEquals("Mastercard",cardType);
	
	String card3 = "342138952685308";
	cardType = CCValidator.getCardType(card3);
	assertEquals("Amex",cardType);

	String card4 = "6011768593893031";
	cardType = CCValidator.getCardType(card4);
	assertEquals("Discover",cardType);
    }
}
