package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** test class for Visa
 * @author Angel Rivera @ Stefana Gloginic
 * @version CS56, Winter 2016
 * @see Visa
 */


public class VisaGenerateTest{
    /*
       test generateCard Method in Visa class
    */
    
    @Test public void testGenerateCard(){

	String GenCard1 = Visa.generateCard();
	boolean result = CCValidator.isValid(GenCard1);

	assertEquals(true, result);
    }
}
