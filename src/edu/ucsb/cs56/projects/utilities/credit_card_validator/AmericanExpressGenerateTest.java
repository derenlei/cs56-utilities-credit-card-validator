package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** test class for AmericanExpress
 * @author Angel Rivera @ Stefana Gloginic
 * @version CS56, Winter 2016
 * @see AmericanExpress
 */


public class AmericanExpressGenerateTest{
    /*
       test generateCard Method in AmericanExpress class
    */

    @Test public void testGenerateCard(){

	String GenCard1 = AmericanExpress.generateCard();
	boolean result = CCValidator.isValid(GenCard1);

	assertEquals(true, result);
    }
}
